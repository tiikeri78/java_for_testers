package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import my.test.addressbook.model.GroupData;
import my.test.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTest extends TestBase {

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
    public void deleteContactFromGroupTest() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts().stream().filter((s) -> s.getGroups().size() < groups.size()).collect(Collectors.toCollection(Contacts::new));
        ContactData editedContact = before.iterator().next();
        int idEditedContact = editedContact.getId();
        Groups contactGroupsBefore = editedContact.getGroups();
        if (contactGroupsBefore.size() == 0) {
            GroupData addToGroup = groups.stream().iterator().next();
            app.goTo().contactPage();
            app.contact().addToGroup(addToGroup, editedContact);
            app.db().contacts();
        }
        contactGroupsBefore = editedContact.getGroups();
        GroupData group = contactGroupsBefore.iterator().next();
        app.goTo().contactPage();
        app.group().selectGroupForSort(group);
        app.contact().selectById(editedContact.getId());
        app.contact().removeFromGroup();
        app.goTo().contactPage();
        Contacts after = app.db().contacts();
        ContactData contactAfter = after.stream().filter(id -> equals(idEditedContact)).iterator().next();
        Groups contactGroupsAfter = contactAfter.getGroups();
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.without(group)));
        verifyContactListInUI();
    }

}
