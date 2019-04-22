package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class EditContactTests extends TestBase{

  @Test
  public void testEditContactTests() throws Exception {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContact(new ContactData("Zelda", "Ivanov", "Santa-Barbara", "09988",
            "krasotka@mail.ry", null), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().gotoHome();
   }
}
