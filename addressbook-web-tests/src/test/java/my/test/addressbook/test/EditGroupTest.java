package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import my.test.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EditGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Admin").withHeader("testers").withFooter("ad21"));
        }
    }

    @Test
    public void editGroupTest() {
        Groups before = app.db().groups();
        GroupData editedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(editedGroup.getId()).withName("Testers").withHeader("work").withFooter("Testers1");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withEdited(editedGroup, group)));
        verifyGroupListInUI();
    }
}