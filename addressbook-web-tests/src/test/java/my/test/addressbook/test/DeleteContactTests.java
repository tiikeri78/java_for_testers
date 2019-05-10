package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class DeleteContactTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().set().size() == 0) {
      app.goTo().groupPage();
      if (app.group().set().size() == 0) {
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
    Set<ContactData> before = app.contact().set();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().set();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
