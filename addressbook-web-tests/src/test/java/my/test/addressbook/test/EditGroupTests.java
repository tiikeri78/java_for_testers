package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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
        Set<GroupData> before = app.group().set();
        GroupData editedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(editedGroup.getId()).withName("Testers").withHeader("work").withFooter("Testers1");
        app.group().modify(group);
        Set<GroupData> after = app.group().set();
        Assert.assertEquals(before.size(), after.size());

        before.remove(editedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }
}
