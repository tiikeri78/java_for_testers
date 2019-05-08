package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
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
    app.getContactHelper().editContact(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Marat", "Notka", "New", "+2569988",
            "m32@mail.ry", null);
    app.getContactHelper().fillContact(contact, false);
    app.getContactHelper().updateContact();
    app.getContactHelper().getMessage();
    app.getNavigationHelper().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
      Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);
  }
}
