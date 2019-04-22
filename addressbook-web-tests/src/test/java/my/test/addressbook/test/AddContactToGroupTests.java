package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class AddContactToGroupTests extends TestBase{

  @Test
  public void testAddContactToGroup() throws Exception {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().selectGroup(new ContactData(null, null, null, null, null, "Workers"));
    app.getContactHelper().addToGroup();
    app.getNavigationHelper().gotoHome();
  }
}
