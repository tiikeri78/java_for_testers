package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class CreateOfGroupTests extends TestBase {

  @Test
  public void testCreateOfGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("Test1", "Test", "Testers1"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
    app.logout();
  }

}
