package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RecordData;

import java.util.List;

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
        List<RecordData> before = app.record().list();
        int index = before.size()-1;
        app.record().delete(index);
        app.wd.switchTo().alert().accept();
        app.goTo().homePage();

        List<RecordData> after = app.record().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before,after);
        }

}
