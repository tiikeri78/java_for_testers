package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class DeleteContactTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().list().size() == 0) {
      app.goTo().groupPage();
      if (app.group().list().size() == 0) {
        app.group().create(new GroupData().withName("Testers").withHeader("testers").withFooter("test5"));
      }
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("Zelda").withLastname("Smith").withAddress("Nevada").withMobileNumber("+195432567").withEmail("krasotka@mail.ry")
              .withGroup("Testers"), true);
    }
  }

  @Test
  public void deleteContactTests() {
    app.goTo().contactPage();
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
