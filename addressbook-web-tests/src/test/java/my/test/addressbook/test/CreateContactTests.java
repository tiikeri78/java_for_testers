package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class CreateContactTests extends TestBase {

    @Test
    public void createContactTests() {
        app.goTo().groupPage();
        if (app.group().set().size() == 0) {
            app.group().create(new GroupData().withName("Test1").withHeader("testers").withFooter("test"));
        }
        app.goTo().contactPage();
        Set<ContactData> before = app.contact().set();
        ContactData contact = new ContactData().withFirstname("Ti").withLastname("Pin").withAddress("Lunapark")
                .withMobileNumber("+987954389876").withEmail("12396@mail.ry").withGroup("Test1");
        app.contact().create(contact, true);
        app.goTo().contactPage();
        Set<ContactData> after = app.contact().set();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

    }
}
