package my.test.addressbook.test;

import org.testng.annotations.Test;

public class EditContactTests extends TestBase{

  @Test
  public void testEditContactTests() throws Exception {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().updateContact();
    app.getNavigationHelper().gotoHome();
   }
}
