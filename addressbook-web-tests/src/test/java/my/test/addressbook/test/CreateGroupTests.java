package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import my.test.addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateGroupTests extends TestBase {

  @Test
  public void createGroupTests() {
    app.goTo().groupPage();
    Groups before = app.group().set();
    GroupData group = new GroupData().withName("Test1").withHeader("test").withFooter("test21");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().set();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
