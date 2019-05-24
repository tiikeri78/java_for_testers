package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import my.test.addressbook.model.GroupData;
import my.test.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class AddContactToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1").withHeader("testers").withFooter("t66"));
        }
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/frog.jpg");
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Zelda").withLastname("Smith").withAddress("Nevada").withMobileNumber("+195432567")
                    .withEmail("krasotka@mail.ry").withPhoto(photo).inGroup(groups.iterator().next()), true);
        }
    }

    @Test
    public void addContactToGroupTest() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts().stream().filter((s) -> s.getGroups().size() < groups.size()).collect(Collectors.toCollection(Contacts::new));
        if(before.size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("People1").withHeader("t11").withFooter("t26"));
            before = app.db().contacts().stream().filter((s) -> s.getGroups().size() < groups.size()).collect(Collectors.toCollection(Contacts::new));
        }
        ContactData editedContact = before.iterator().next();
        Groups contactGroupsBefore = editedContact.getGroups();
        groups.removeAll(contactGroupsBefore);
        app.goTo().contactPage();
        app.contact().addToGroup(groups, editedContact);
        app.goTo().contactPage();
        Groups contactGroupsAfter = editedContact.getGroups();
        assertEquals(contactGroupsAfter.size(), contactGroupsBefore.size());
        verifyContactListInUI();
    }
}
