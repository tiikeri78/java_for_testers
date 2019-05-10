package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import my.test.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EditGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().set().size() == 0){
            app.group().create(new GroupData().withName("Admin").withHeader("testers").withFooter("ad21"));
        }
    }

    @Test
    public void editGroupTests() {
        Groups before = app.group().set();
        GroupData editedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(editedGroup.getId()).withName("Testers").withHeader("work").withFooter("Testers1");
        app.group().modify(group);
        Groups after = app.group().set();
        System.out.println(before.withEdited(editedGroup, group));
        System.out.println(after);
        assertEquals(before.size(), after.size());
        assertThat(after, equalTo(before.withEdited(editedGroup, group)));
    }
}
