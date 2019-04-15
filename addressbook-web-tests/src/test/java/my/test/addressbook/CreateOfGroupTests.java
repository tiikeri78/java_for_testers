package my.test.addressbook;

import org.testng.annotations.Test;

public class CreateOfGroupTests extends TestBase{

  @Test
  public void testCreateOfGroup() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Testers", "Test", "Testers1"));
    submitGroupCreation();
    returnToGroupPage();
    logout();
  }

}
