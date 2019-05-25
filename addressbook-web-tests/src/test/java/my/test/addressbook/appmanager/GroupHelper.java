package my.test.addressbook.appmanager;

import my.test.addressbook.model.GroupData;
import my.test.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static my.test.addressbook.test.TestBase.app;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {

        click(By.name("new"));
    }

    public void selectById(int id) {

        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void editGroup() {

        click(By.name("edit"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void updateGroup() {

        click(By.name("update"));
    }

    private Groups groupCache = null;

    public void selectGroup(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    }

    public void selectGroupForSort(GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }

    public void delete(GroupData deletedGroup) {
        selectById(deletedGroup.getId());
        deleteGroup();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectById(group.getId());
        editGroup();
        fillGroupForm(group);
        updateGroup();
        groupCache = null;
        app.goTo().groupPage();
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {

        return isElementPresent(By.name("selected[]"));
    }

    public int count() {

        return wd.findElements(By.name("selected[]")).size();
    }

    public Groups set() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }
}
