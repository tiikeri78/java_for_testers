package my.test.mantis.test;

import my.test.mantis.appmanager.HttpSession;
import my.test.mantis.model.MailMessage;
import my.test.mantis.model.UserData;
import my.test.mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPasswordTest extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        app.registration().loginAdmin();
        Users users = app.db().users();
        UserData editedUser = users.iterator().next();
        app.registration().changePassword(editedUser.getUsername());
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, editedUser.getEmail());
        String password = "password";
        app.registration().finish(confirmationLink, password);
        HttpSession session = app.newSession();
        session.login(editedUser.getUsername(), password);
        assertTrue(session.isLoggedInAs(editedUser.getUsername()));
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
