package my.test.addressbook.appmanager;

import my.test.addressbook.model.ContactData;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends BaseHelper {
    private boolean acceptNextAlert = true;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initAddContact() {
        click(By.linkText("add new"));
    }

    public void fillContact(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobileNumber());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectGroup(ContactData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getGroup());
    }

    public void submitAddNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void editContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void deleteContact() {
        acceptNextAlert = true;
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public WebElement getMessage() {
        return wd.findElement(By.cssSelector(".msgbox"));
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    public void addToGroup() {
        click(By.name("add"));
    }

    public void createContact(ContactData contact, boolean creation) {
        initAddContact();
        fillContact(contact, creation);
        submitAddNewContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> lines = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : lines) {
            List<WebElement> columns = element.findElements(By.tagName("td"));
            String firstname = columns.get(2).getText();
            String lastname = columns.get(1).getText();
            String address = columns.get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstname, lastname, address, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
