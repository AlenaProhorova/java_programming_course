package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RecordModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.db().records().size() ==0){
            File photo = new File("src/test/resources/avatar.png");
        /*    app.record().create(new RecordData()
                    .withFirstname("Ivan")
                    .withLastname("Ivanov")
                    .withAddress("Lenina Street, 5/3")
                    .withHomePhone("89634733435")
                    .withEmail("ivanov@ya.ru")
                    .withPhoto(photo)
                   .withGroup("test1"),true);*/
        }
    }

    @Test  //(enabled = false)
    public void testRecordModification() {
        app.goTo().homePage();
        File photo = new File("src/test/resources/avatar.png");
        Records before = app.db().records();
        RecordData modifiedRecord = before.iterator().next();
        RecordData record = new RecordData()
                .withId(modifiedRecord.getId())
                .withFirstname("Nikita")
                .withLastname("Nikitov")
                .withAddress("Lenina Street, 5/3")
                .withHomePhone("89634733435")
                .withEmail("ivanov@ya.ru")
                .withPhoto(photo);
        app.record().modify(record);
        app.goTo().homePage();
        assertThat(app.record().count(), equalTo(before.size()));
        Records after = app.db().records();
        //Records after = app.db().records();
        assertThat(after, equalTo(before.without(modifiedRecord).withAdded(record)));
        VerifyRecordListInUI();
    }


}
