package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test0"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group().all();
        GroupData modifyGroup = before.iterator().next();
        //int index = before.size() - 1;
        GroupData group = new GroupData()
                .withId(modifyGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
       // int before = app.group().getGroupCount();
        app.group().modify(group);
        Groups after = app.group().all();
        //int after = app.group().getGroupCount();
        assertEquals(after.size(), before.size());

      //  before.remove(modifyGroup);
       // before.add(group);

       /* Comparator<? super GroupData> byId = (g1, g2) ->  Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);*/
      //  Assert.assertEquals(before,after);
        assertThat(after,equalTo(before.without(modifyGroup).withAdded(group)));
      //  Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }

}
