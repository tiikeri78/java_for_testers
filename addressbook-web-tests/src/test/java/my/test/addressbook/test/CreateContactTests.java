package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactTests extends TestBase {

  @Test
  public void testAddNewContact() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test1", "testers", null));
    }
      app.getNavigationHelper().gotoHome();
      int before = app.getContactHelper().getContactCount();
      app.getContactHelper().createContact(new ContactData("Ernest", "Oreiro", "Nevada", "+195432567", "0987@mail.ry",
              "Test1"), true);
      app.getNavigationHelper().gotoHome();
      int after = app.getContactHelper().getContactCount();
      Assert.assertEquals(after, before + 1);
    }
}
