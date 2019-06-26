package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RecordCreationTests extends TestBase{

  @Test //(enabled = false)
  public void testRecordCreation() throws Exception {
    app.goTo().homePage();
    Records before = app.record().all();
    RecordData record = new RecordData()
            .withFirstname("Ivan")
            .withLastname("Ivanov")
            .withAddress("Lenina Street, 5/3")
            .withHomePhone("89634733435")
            .withEmail("ivanov@ya.ru")
            .withGroup("test0");
    app.record().create(record,true);
    app.goTo().homePage();
    assertThat(app.record().count(), equalTo(before.size()+1));
    Records after = app.record().all();
    //assertThat(after.size(), equalTo( before.size()+1));
    //record.withId(after.stream().mapToInt((r) -> r.getId()).max().getAsInt());
   // before.add(record);

  /*  Comparator<? super RecordData> byId = (r1, r2) ->  Integer.compare(r1.getId(), r2.getId());
    before.sort(byId);
    after.sort(byId);*/
   // assertEquals(before,after);
    assertThat(after,equalTo(
            before.withAdded(record.withId(after.stream().mapToInt((r) -> r.getId()).max().getAsInt()))));
   // app.getSessionHelper().logout();
  }

}
