package my.test.addressbook;

import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase{

  @Test
  public void testAddNewContact() throws Exception {
    gotoNewContactForm();
    addNewContact(new ContactData("Zelda", "Smith", "Nevada", "+195432567", "krasotka@mail.ry"));
    submitAddNewContact();
    logout();
  }

}
