package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.RecordData;

import java.util.Comparator;
import java.util.List;

public class RecordCreationTests extends TestBase{

  @Test //(enabled = false)
  public void testRecordCreation() throws Exception {
    app.goTo().homePage();
    List<RecordData> before = app.record().list();
    RecordData record = new RecordData()
            .withFirstname("Ivan")
            .withLastname("Ivanov")
            .withAddress("Lenina Street, 5/3")
            .withHometelefon("89634733435")
            .withEmail("ivanov@ya.ru")
            .withGroup("test0");
    app.record().create(record,true);
    app.goTo().homePage();
    List<RecordData> after = app.record().list();
    Assert.assertEquals(after.size(), before.size()+1);
    before.add(record);

    Comparator<? super RecordData> byId = (r1, r2) ->  Integer.compare(r1.getId(), r2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
   // app.getSessionHelper().logout();
  }

}
