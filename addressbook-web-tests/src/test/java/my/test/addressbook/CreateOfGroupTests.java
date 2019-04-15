package my.test.addressbook;

import org.testng.annotations.Test;

public class CreateOfGroupTests extends TestBase{

  @Test
  public void testCreateOfGroup() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("Test1", "Test", "Testers1"));
    app.submitGroupCreation();
    app.returnToGroupPage();
    app.logout();
  }

}
