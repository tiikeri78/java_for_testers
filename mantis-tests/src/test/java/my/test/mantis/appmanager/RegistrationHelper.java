package my.test.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends BaseHelper{

    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

    public void loginAdmin(){
        String username = app.getProperty("web.adminLogin");
        String password = app.getProperty("web.adminPassword");
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти']"));
    }

    public void login(String username, String password){
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти']"));
    }

    public void changePassword(String username) {
        wd.findElement(By.linkText("управление")).click();
        wd.findElement(By.linkText("Управление пользователями")).click();
        click(By.linkText(username));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void logout(){
        wd.findElement(By.linkText("выход")).click();
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }
}
