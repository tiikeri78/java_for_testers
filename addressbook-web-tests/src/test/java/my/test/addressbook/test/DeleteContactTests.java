package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase{

  @Test
  public void deleteContactTests() {
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHelper().isThereAGroup()){
        app.getGroupHelper().createGroup(new GroupData("Test1", "testers", null));
      }
      app.getNavigationHelper().gotoHome();
      app.getContactHelper().createContact(new ContactData("Zelda", "Smith", "Nevada", "+195432567", "krasotka@mail.ry",
              "Test1"),true);
    }
    app.getNavigationHelper().gotoHome();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().gotoHome();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
