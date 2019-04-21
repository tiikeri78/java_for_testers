package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class CreateGroupTests extends TestBase {

  @Test
  public void testCreateGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("Test1", null, null));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
  }
}