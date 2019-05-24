package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import my.test.addressbook.model.GroupData;
import my.test.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class DeleteContactFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
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
        ContactData group = new ContactData().inGroup(groups.iterator().next());
        System.out.println(group);
        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        app.contact().selectGroupForSort(new ContactData().inGroup(groups.iterator().next()));
        if (! app.contact().isThereAContact()){
            app.stop();
           // app.contact().selectGroupForSort();
        }
        ContactData editedContact = before.iterator().next();
        app.contact().selectById(editedContact.getId());
        app.contact().removeFromGroup();
        app.goTo().contactPage();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        verifyContactListInUI();
    }
}
