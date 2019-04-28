package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateGroupTests extends TestBase {

  @Test
  public void testCreateGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("Holidays", "testers1", "t3"));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);
  }
}
