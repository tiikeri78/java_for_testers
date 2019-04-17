package my.test.addressbook.test;

import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase{

  @Test
  public void testDeleteContactTests() throws Exception {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
  }
}
