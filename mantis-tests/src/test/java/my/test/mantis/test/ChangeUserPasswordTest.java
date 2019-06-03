package my.test.mantis.test;

import my.test.mantis.model.MailMessage;
import my.test.mantis.model.UserData;
import my.test.mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;
import java.util.Objects;

public class ChangeUserPasswordTest extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() {
        app.registration().loginAdmin();
        Users users = app.db().users();
        if (users.size() == 1){
            long now = System.currentTimeMillis();
            String user = String.format("username%s", now);
            String password = "password";
            String email = String.format("username%s@localhost.localdomain", now);
            app.registration().start(user, email);
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 60000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            app.registration().finish(confirmationLink, password);
            users = app.db().users();
        }
        UserData editedUser = users.iterator().next();
        String usernameAdmin = app.getProperty("web.adminLogin");
        while (editedUser.getUsername().equals(usernameAdmin)){
            editedUser = users.iterator().next();
        }
        int idEditedUser = editedUser.getId();
        app.registration().changePassword(editedUser.getUsername());
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, editedUser.getEmail());
        String password = "password";
        app.registration().finish(confirmationLink, password);
        app.registration().logout();
        Users usersAfter = app.db().users();
        UserData userAfter = usersAfter.stream().filter(data -> Objects.equals(data.getId(), idEditedUser)).findFirst().get();
        String user = userAfter.getUsername();
        String passwordNew = userAfter.getPassword();
        app.registration().login(user, passwordNew);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
