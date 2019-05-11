package my.test.addressbook.appmanager;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

import static my.test.addressbook.test.TestBase.app;
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

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void editContact(int id) {
        wd.findElement(By.cssSelector("a[href='" + "edit.php?id=" + id + "']")).click();
    }

    public void updateContact() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void modify(ContactData contact) {
        selectById(contact.getId());
        editContact(contact.getId());
        fillContact(contact, false);
        updateContact();
        getMessage();
        app.goTo().contactPage();
    }

    public void deleteContact() {
        acceptNextAlert = true;
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void delete(ContactData deletedContact) {
        selectById(deletedContact.getId());
        deleteContact();
        getMessage();
        app.goTo().contactPage();
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

    public void create(ContactData contact, boolean creation) {
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

    public Contacts set() {
        Contacts contacts = new Contacts();
        List<WebElement> lines = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : lines) {
            List<WebElement> columns = element.findElements(By.tagName("td"));
            String firstname = columns.get(2).getText();
            String lastname = columns.get(1).getText();
            String address = columns.get(3).getText();
            String email = columns.get(4).getText();
            String mobileNumber = columns.get(5).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address)
                    .withMobileNumber(mobileNumber).withEmail(email));
        }
        return contacts;
    }
}
