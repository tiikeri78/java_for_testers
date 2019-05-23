package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import my.test.addressbook.model.GroupData;
import my.test.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddContactToGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1").withHeader("testers").withFooter("t66"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("Zelda").withLastname("Smith").withAddress("Nevada").withMobileNumber("+195432567")
              .withEmail("krasotka@mail.ry"), true);
    }
  }

  @Test
  public void addContactToGroupTests() {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    app.goTo().contactPage();
    ContactData editedContact = before.iterator().next();
    app.contact().selectById(editedContact.getId());
    app.contact().selectGroup(new ContactData().inGroup(groups.iterator().next()));
    app.contact().addToGroup();
    app.goTo().contactPage();
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    verifyContactListInUI();
  }
}
