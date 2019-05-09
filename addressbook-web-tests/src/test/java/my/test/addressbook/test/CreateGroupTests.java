package my.test.addressbook.test;

import my.test.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class CreateGroupTests extends TestBase {

  @Test
  public void createGroupTests() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("Test1").withHeader("test").withFooter("test21");
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
    before.add(group);
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
