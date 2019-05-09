package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class EditGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData().withName("Admin").withHeader("testers").withFooter("ad21"));
        }
    }

    @Test
    public void editGroupTests() {
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        GroupData group = new GroupData().withId(before.get(index).getId()).withName("Testers").withHeader("work").withFooter("Testers1");
        app.group().modify(index, group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(before.size(), after.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
