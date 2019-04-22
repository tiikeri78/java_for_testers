package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class AddContactToGroupTests extends TestBase{

  @Test
  public void testAddContactToGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("Test1", "testers", null));
    }
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().selectGroup(new ContactData(null, null, null, null, null, "Test1"));
    app.getContactHelper().addToGroup();
    app.getNavigationHelper().gotoHome();
  }
}
