package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    app.getNavigationHelper().gotoNewContactForm();
    app.getContactHelper().addNewContact(new ContactData("Zelda", "Smith", "Nevada", "+195432567", "krasotka@mail.ry"));
    app.getContactHelper().submitAddNewContact();
    app.getNavigationHelper().gotoHome();
    app.getSessionHelper().logout();
  }

}
