package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import my.test.addressbook.model.GroupData;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContactTests extends TestBase {

    @Test
    public void createContactTests() {
        app.goTo().groupPage();
        if (app.group().set().size() == 0) {
            app.group().create(new GroupData().withName("Test1").withHeader("testers").withFooter("test"));
        }
        app.goTo().contactPage();
        Contacts before = app.contact().set();
        File photo = new File("src/test/resources/frog.jpg");
        ContactData contact = new ContactData().withFirstname("Ti").withLastname("Pin").withAddress("Lunapark")
                .withMobileNumber("+987954389876").withEmail("12396@mail.ry").withGroup("Test1").withPhoto(photo);
        app.contact().create(contact, true);
        app.goTo().contactPage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().set();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
