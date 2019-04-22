package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class CreateGroupTests extends TestBase {

  @Test
  public void testCreateGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("Test1", "test", "test"));
  }
}
