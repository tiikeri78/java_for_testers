package my.test.addressbook.test;

import org.testng.annotations.Test;

public class DeleteGroupTests extends TestBase{

    @Test
    public void testDeleteGroup() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteGroup();
        app.getNavigationHelper().gotoGroupPage();
        }
}
