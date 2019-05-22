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

public class EditContactTest extends TestBase {

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
                    .withEmail("krasotka@mail.ry").withGroup("Test1").withPhoto(photo), true);
        }

    }

    @Test
    public void editContactTests() {
        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        ContactData editedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(editedContact.getId()).withFirstname("Marat").withLastname("Notka").withAddress("New")
                .withMobileNumber("+2569988").withEmail("m32@mail.ry");
        app.contact().modify(contact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withEdited(editedContact, contact)));
        verifyContactListInUI();
    }
}
