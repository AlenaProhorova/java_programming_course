package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    //int before = app.group().count();
    GroupData group = new GroupData().withName("test0");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    //int after = app.group().count();

  /*  int max = 0;
    for (GroupData g : after){
      if (g.getId()>max){
        max = g.getId();
      }
    }*/

   // Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
  //  int max =  after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

  //  group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
   // before.add(group);
    /*Comparator<? super GroupData> byId = (g1, g2) ->  Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);*/
   // Assert.assertEquals(before,after);
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
   // Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }

  @Test (enabled = false)
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test1");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));

  }

}
