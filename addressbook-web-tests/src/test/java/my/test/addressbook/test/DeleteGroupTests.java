package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class DeleteGroupTests extends TestBase{

    @Test
    public void testDeleteGroup() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Test1", "testers", null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().gotoGroupPage();
        }
}
