package my.test.addressbook.appmanager;

import my.test.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewContact(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobileNumber());
        type(By.name("email"), contactData.getEmail());
    }

    public void submitAddNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }
}
