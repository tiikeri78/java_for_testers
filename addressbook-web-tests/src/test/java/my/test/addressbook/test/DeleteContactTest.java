package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import my.test.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteContactTest extends TestBase{

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
    Contacts before = app.contact().set();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.contact().set();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
