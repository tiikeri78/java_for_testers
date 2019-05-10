package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import my.test.addressbook.model.Groups;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateGroupTests extends TestBase {

  @Test
  public void createGroupTests() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().set();
    GroupData group = new GroupData().withName("Test1").withHeader("test").withFooter("test21");
    app.group().create(group);
    Set<GroupData> after = app.group().set();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            ((Groups) before).withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
