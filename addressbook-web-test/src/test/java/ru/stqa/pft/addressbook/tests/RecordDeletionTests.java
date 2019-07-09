package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RecordDeletionTests extends  TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.db().records().size() ==0){
            app.record().create(new RecordData()
                    .withFirstname("Ivan")
                    .withLastname("Ivanov")
                    .withAddress("Lenina Street, 5/3")
                    .withHomePhone("89634733435")
                    .withEmail("ivanov@ya.ru")
                    .withGroup("test1"),true);
        }
    }

    @Test
    public void testRecordDeletion() {
        Records before = app.db().records();
        RecordData deletedRecord = before.iterator().next();
        app.record().delete(deletedRecord);
        app.wd.switchTo().alert().accept();
        app.goTo().homePage();
        assertThat(app.record().count(), equalTo(before.size()-1));
        Records after = app.db().records();
        assertThat(after,equalTo(before.without(deletedRecord)));

        }

}
