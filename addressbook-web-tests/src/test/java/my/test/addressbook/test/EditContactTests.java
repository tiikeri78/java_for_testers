package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class EditContactTests extends TestBase{

  @Test
  public void editContactTests() {
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
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().editContact();
    app.getContactHelper().fillContact(new ContactData("Zelda", "Ivanov", "Santa-Barbara", "09988",
            "krasotka@mail.ry", null), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
