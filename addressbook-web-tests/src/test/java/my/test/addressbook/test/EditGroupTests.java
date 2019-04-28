package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditGroupTests extends TestBase {

    @Test
    public void testEditGroup() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Test1", "testers", null));
        }
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup(before-1);
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("Workers", "work", "Testers1"));
        app.getGroupHelper().updateGroup();
        app.getNavigationHelper().gotoGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, after);
    }
}
