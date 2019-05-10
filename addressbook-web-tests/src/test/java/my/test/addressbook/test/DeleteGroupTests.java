package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import my.test.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().set().size() == 0) {
            app.group().create(new GroupData().withName("Admin").withHeader("testers").withFooter("ad21"));
        }
    }

    @Test
    public void deleteGroupTests() {
        Groups before = app.group().set();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.group().set();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
