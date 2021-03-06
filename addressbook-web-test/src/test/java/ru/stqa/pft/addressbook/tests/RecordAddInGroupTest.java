package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;
import java.io.File;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecordAddInGroupTest extends TestBase{

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

        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("test0"));
        }

    }

    @Test
    public void testRecordAddInGroup() {
        Records before = app.db().records();
        Groups groups = app.db().groups();
        RecordData addedRecord = before.iterator().next();
        Groups groupFromRecord = addedRecord.getGroups();
        GroupData addedGroup = groups.iterator().next();

        if ((groupFromRecord).equals(groups)){
            app.record().deleteRecordGroup(addedRecord,addedGroup);
            Records test = app.db().records();
            addedRecord = test.iterator().next().withId(addedRecord.getId());
            groupFromRecord =  addedRecord.getGroups();
        }


        if (groupFromRecord.size() < groups.size()){
            groups.removeAll(groupFromRecord);
            addedGroup = groups.iterator().next();
            app.record().selectRecordById(addedRecord.getId());
            app.record().addRecordGroup(addedGroup);
            app.goTo().homePage();
        }

        Records after = app.db().records();
        RecordData recordAfterAddedGroup = after.iterator().next().withId(addedRecord.getId());
        assertThat(recordAfterAddedGroup.getGroups().without(addedGroup), equalTo(addedRecord.getGroups()));

    }

}



