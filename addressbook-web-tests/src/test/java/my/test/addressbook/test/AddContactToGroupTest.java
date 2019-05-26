package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import my.test.addressbook.model.GroupData;
import my.test.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Contacts contacts = app.db().contacts();
        ContactData editedContact = contacts.iterator().next();
        int idEditedContact = editedContact.getId();
        Groups contactGroupsBefore = editedContact.getGroups();
        groups.removeAll(contactGroupsBefore);
        while (groups.size() == 0){
            editedContact = contacts.iterator().next();
        }
        GroupData groupForAdd = groups.stream().iterator().next();
        app.goTo().contactPage();
        app.contact().addToGroup(groupForAdd, editedContact);
        app.goTo().contactPage();
        Contacts after = app.db().contacts();
        ContactData contactAfter = after.stream().filter(data -> Objects.equals(data.getId(), idEditedContact)).findFirst().get();
        Groups contactGroupsAfter = contactAfter.getGroups();
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(groupForAdd)));

        verifyContactListInUI();
    }
}
