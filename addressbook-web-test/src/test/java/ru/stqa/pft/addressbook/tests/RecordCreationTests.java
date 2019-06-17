package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.RecordData;

import java.util.Comparator;
import java.util.List;

public class RecordCreationTests extends TestBase{

  @Test
  public void testRecordCreation() throws Exception {
  //  app.getNavigationHelper().gotoRecordPage();
    List<RecordData> before = app.getRecordHelper().getRecordList();
    RecordData record = new RecordData("Ivan", "Ivanov", "Lenina Street, 5/3", "89634733435", "ivanov@ya.ru","test0");
    app.getRecordHelper().createRecord(record,true);
    app.getNavigationHelper().gotoHomePage();
    List<RecordData> after = app.getRecordHelper().getRecordList();
    Assert.assertEquals(after.size(), before.size()+1);
    before.add(record);

    Comparator<? super RecordData> byId = (r1, r2) ->  Integer.compare(r1.getId(), r2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
    app.getSessionHelper().logout();
  }

}
