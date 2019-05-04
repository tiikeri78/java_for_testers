package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().editContact();
    app.getContactHelper().fillContact(new ContactData("Zelda", "Ivanov", "Santa-Barbara", "09988",
            "krasotka@mail.ry", null), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().gotoHome();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
   }
}
