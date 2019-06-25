package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RecordDeletionTests extends  TestBase{

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

    @Test
    public void testRecordDeletion() {
        Records before = app.record().all();
        RecordData deletedRecord = before.iterator().next();
       // int index = before.size()-1;
        app.record().delete(deletedRecord);
        app.wd.switchTo().alert().accept();
        app.goTo().homePage();
        assertThat(app.record().count(), equalTo(before.size()-1));
        Records after = app.record().all();
        //assertEquals(after.size(), before.size() - 1);

        //before.remove(deletedRecord);
        assertThat(after,equalTo(before.without(deletedRecord)));
        //Assert.assertEquals(before,after);
        }

}
