package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class EditGroupTests extends TestBase {

    @Test
    public void testEditGroup() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("Workers", null, "Testers1"));
        app.getGroupHelper().updateGroup();
        app.getNavigationHelper().gotoGroupPage();
    }
}
