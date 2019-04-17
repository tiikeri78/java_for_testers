package my.test.addressbook.test;

import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase{

    @Test
    public void testAddNewContact() throws Exception {
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().submitDeleteContact();
        app.getNavigationHelper().gotoHome();
        app.getSessionHelper().logout();
    }
}
