package my.test.mantis.test;

import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {

    @Test
    public void testRegistration(){
        app.registration().start("user", "441@mail.ru");
    }
}
