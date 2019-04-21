package my.test.addressbook.test;

import org.testng.annotations.Test;

public class AddContactToGroupTests extends TestBase{

  @Test
  public void testAddContactToGroup() throws Exception {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().selectGroup();
    app.getContactHelper().addToGroup();
    app.getNavigationHelper().gotoHome();
  }
}
