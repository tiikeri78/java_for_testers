package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import my.test.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EditContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().set().size() == 0) {
            app.goTo().groupPage();
            if (app.group().set().size() == 0) {
                app.group().create(new GroupData().withName("Test1").withHeader("testers").withFooter("t66"));
            }
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Zelda").withLastname("Smith").withAddress("Nevada").withMobileNumber("+195432567")
                    .withEmail("krasotka@mail.ry").withGroup("Test1"), true);
        }
    }

    @Test
    public void editContactTests() {
        app.goTo().contactPage();
        Contacts before = app.contact().set();
        ContactData editedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(editedContact.getId()).withFirstname("Marat").withLastname("Notka").withAddress("New")
                .withMobileNumber("+2569988").withEmail("m32@mail.ry");
        app.contact().modify(contact);
        Contacts after = app.contact().set();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withEdited(editedContact, contact)));
    }
}
