package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RecordData;

import java.util.Comparator;
import java.util.List;

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
        List<RecordData> before = app.record().list();
        int index = before.size() - 1;
        RecordData record = new RecordData()
                .withId(before.get(index).getId())
                .withFirstname("Nikita")
                .withLastname("Nikitov")
                .withAddress("Lenina Street, 5/3")
                .withHometelefon("89634733435")
                .withHometelefon("ivanov@ya.ru");
        app.record().modify(index, record);
        app.goTo().homePage();
        List<RecordData> after = app.record().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(record);

        Comparator<? super RecordData> byId = (r1, r2) ->  Integer.compare(r1.getId(), r2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }


}
