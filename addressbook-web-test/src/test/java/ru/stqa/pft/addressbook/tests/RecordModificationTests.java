package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RecordModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.record().list().size() ==0){
            app.record().create(new RecordData()
                    .withFirstname("Ivan")
                    .withLastname("Ivanov")
                    .withAddress("Lenina Street, 5/3")
                    .withHometelefon("89634733435")
                    .withEmail("ivanov@ya.ru")
                    .withGroup("test1"),true);
        }
    }

    @Test  //(enabled = false)
    public void testRecordModification() {
        Records before = app.record().all();
        RecordData modifiedRecord = before.iterator().next();
      //  int index = before.size() - 1;
        RecordData record = new RecordData()
                .withId(modifiedRecord.getId())
                .withFirstname("Nikita")
                .withLastname("Nikitov")
                .withAddress("Lenina Street, 5/3")
                .withHometelefon("89634733435")
                .withHometelefon("ivanov@ya.ru");
        app.record().modify(record);
        app.goTo().homePage();
        Records after = app.record().all();
        assertEquals(after.size(), before.size());

       // before.remove(modifiedRecord);
       // before.add(record);

       /* Comparator<? super RecordData> byId = (r1, r2) ->  Integer.compare(r1.getId(), r2.getId());
        before.sort(byId);
        after.sort(byId);*/
       // assertEquals(before,after);
        assertThat(after, equalTo(before.without(modifiedRecord).withAdded(record)));
    }


}
