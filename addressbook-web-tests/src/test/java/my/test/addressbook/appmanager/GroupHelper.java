package my.test.addressbook.appmanager;

import my.test.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void editGroup() {
        click(By.name("edit"));
    }

    public void modify(int index, GroupData group) {
        selectGroup(index);
        editGroup();
        fillGroupForm(group);
        updateGroup();
        app.goTo().groupPage();
    }

    public void delete(int index) {
        selectGroup(index);
        deleteGroup();
        app.goTo().groupPage();
    }

    public void updateGroup() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {

        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {

        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> list() {
       List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //element.findElement(By.name("selected[]")).click();
            //click(By.name("edit"));
            //String header = wd.findElement(By.name("group_header")).getAttribute("cols");
            //String footer = wd.findElement(By.name("group_footer")).getAttribute("cols");
            //click(By.linkText("groups"));
            groups.add(new GroupData().withId(id).withName(name));
       }
        return groups;
    }

    public Set<GroupData> set() {
        Set<GroupData> groups = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
