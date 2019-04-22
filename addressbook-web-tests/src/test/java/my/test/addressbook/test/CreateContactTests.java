package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class CreateContactTests extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    app.getContactHelper().initAddContact();
    app.getContactHelper().fillContact(new ContactData("Zelda", "Smith", "Nevada", "+195432567", "krasotka@mail.ry",
            "Test1"),true);
    app.getContactHelper().submitAddNewContact();
    app.getNavigationHelper().gotoHome();
  }
}
