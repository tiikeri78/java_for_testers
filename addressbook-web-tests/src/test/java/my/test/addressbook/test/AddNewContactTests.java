package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    app.getNavigationHelper().gotoNewContactForm();
    app.addNewContact(new ContactData("Zelda", "Smith", "Nevada", "+195432567", "krasotka@mail.ry"));
    app.submitAddNewContact();
    app.logout();
  }

}
