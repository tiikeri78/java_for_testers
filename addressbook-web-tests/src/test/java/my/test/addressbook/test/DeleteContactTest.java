package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import my.test.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteContactTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1").withHeader("testers").withFooter("t66"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      File photo = new File("src/test/resources/frog.jpg");
      app.contact().create(new ContactData().withFirstname("Zelda").withLastname("Smith").withAddress("Nevada").withMobileNumber("+195432567")
              .withEmail("krasotka@mail.ry").withPhoto(photo), true);
    }
  }

  @Test
  public void deleteContactTest() {
    Contacts before = app.db().contacts();
    app.goTo().contactPage();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }
}
