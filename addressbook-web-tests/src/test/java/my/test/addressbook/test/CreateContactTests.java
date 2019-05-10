package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class CreateContactTests extends TestBase {

    @Test
    public void createContactTests() {
        app.goTo().groupPage();
        if (app.group().set().size() == 0) {
            app.group().create(new GroupData().withName("Test1").withHeader("testers").withFooter("test"));
        }
        app.goTo().contactPage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstname("Ti").withLastname("Pin").withAddress("Lunapark")
                .withMobileNumber("+987954389876").withEmail("12396@mail.ry").withGroup("Test1");
        app.contact().create(contact, true);
        app.goTo().contactPage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);

        contact.withId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    }
}
