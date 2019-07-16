package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecordDeleteFromGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        File photo = new File("src/test/resources/avatar.png");
        Groups groups = app.db().groups();
        RecordData newRecord = new RecordData()
                .withFirstname("Ivan")
                .withLastname("Ivanov")
                .withAddress("Lenina Street, 5/3")
                .withHomePhone("89634733435")
                .withEmail("ivanov@ya.ru")
                .withPhoto(photo)
                .inGroup(groups.iterator().next());
        if (app.db().records().size() ==0){
            app.record().create(newRecord, true);
        }
    }

    @Test
    public void testRecordDeleteFromGroup() {
        app.goTo().homePage();
        Records before = app.db().records();
        Groups groups = app.db().groups(); //группы из БД
        RecordData deleteRecord = before.iterator().next();
        Groups groupFromRecord = deleteRecord.getGroups();

      /*  if (deleteRecord.getGroups().size() == 0){
            app.record().selectRecordById(deleteRecord.getId());
            app.record().addRecordGroup(groups.iterator().next());
            app.goTo().homePage();
        }*/

        //groups.removeAll(groupFromRecord);

        for (GroupData groupDelete : groupFromRecord) {
            app.record().deleteRecordGroup(deleteRecord,groupDelete);
            app.goTo().homePage();
        }

        assertThat(app.db().records(), equalTo(before));

    }

}
