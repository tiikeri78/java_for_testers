package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddContactToGroupTests extends TestBase{

  @Test
  public void addContactToGroupTests() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("Test1", "testers", null));
    }
    app.getNavigationHelper().gotoHome();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().selectGroup(new ContactData(null, null, null, null, null, "Masters"));
    app.getContactHelper().addToGroup();
    app.getNavigationHelper().gotoHome();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
