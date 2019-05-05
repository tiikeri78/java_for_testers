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
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("Test1", "testers", null));
        }
        app.getNavigationHelper().gotoHome();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Ti", "Pin", "Lunapark", "+9879543898076", "12396@mail.ry",
                "Test1");
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);

        contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    }
}
