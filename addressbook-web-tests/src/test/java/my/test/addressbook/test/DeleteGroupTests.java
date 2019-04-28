package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteGroupTests extends TestBase{

    @Test
    public void testDeleteGroup() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Test1", "testers", null));
        }
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().gotoGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before - 1);
        }
}
