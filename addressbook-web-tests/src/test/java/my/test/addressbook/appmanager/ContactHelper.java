package my.test.addressbook.appmanager;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import my.test.addressbook.model.GroupData;
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
        type(By.name("home"), contactData.getHomeNumber());
        type(By.name("mobile"), contactData.getMobileNumber());
        type(By.name("work"), contactData.getWorkNumber());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attachFile(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0){
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups()
                .iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectGroup(ContactData group) {
         new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getGroups()
                 .iterator().next().getName());
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

    public void deleteContact() {
        acceptNextAlert = true;
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public WebElement getMessage() {

        return wd.findElement(By.cssSelector(".msgbox"));
    }

    public void addToGroup() {
        click(By.name("add"));
    }

    public void removeFromGroup() {
        click(By.name("remove"));
    }

    private void initContactModificationById(int id){
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public ContactData infoFromEditForm(ContactData contact){
        initContactModificationById(contact.getId());
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withAddress(address).withHomeNumber(home).withMobileNumber(mobile).withWorkNumber(work).withEmail(email)
                .withEmail2(email2).withEmail3(email3);
    }

    private Contacts contactCache = null;

    public void modify(ContactData contact) {
        selectById(contact.getId());
        editContact(contact.getId());
        fillContact(contact, false);
        updateContact();
        getMessage();
        contactCache = null;
        app.goTo().contactPage();
    }

    public void addToGroup(GroupData group, ContactData contact) {
        selectById(contact.getId());
        app.group().selectGroup(group);
        addToGroup();
    }

    public void delete(ContactData deletedContact) {
        selectById(deletedContact.getId());
        deleteContact();
        getMessage();
        contactCache = null;
        app.goTo().contactPage();
    }

    public void create(ContactData contact, boolean creation) {
        initAddContact();
        fillContact(contact, creation);
        submitAddNewContact();
        contactCache = null;
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

    public boolean isThereAContact() {
        return isElementPresent(By.name("entry"));
    }

    public int count() {

        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts set() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> lines = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : lines) {
            List<WebElement> columns = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String firstname = columns.get(2).getText();
            String lastname = columns.get(1).getText();
            String address = columns.get(3).getText();
            String allEmails = columns.get(4).getText();
            String allPhones  = columns.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address)
                    .withAllPhones(allPhones).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }
}
