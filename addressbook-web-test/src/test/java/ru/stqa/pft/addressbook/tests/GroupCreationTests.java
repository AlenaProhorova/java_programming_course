package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    //int before = app.group().getGroupCount();
    GroupData group = new GroupData().withName("test0");
    app.group().create(group);
    Groups after = app.group().all();
    //int after = app.group().getGroupCount();
    assertThat(after.size(), equalTo(before.size() + 1));

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

}
