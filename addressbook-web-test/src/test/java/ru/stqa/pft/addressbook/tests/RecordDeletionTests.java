package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.RecordData;

import java.util.List;

public class RecordDeletionTests extends  TestBase{
    @Test
    public void testRecordDeletion() {

        if (! app.getRecordHelper().isThereARecord()){
            app.getRecordHelper().createRecord(new RecordData("Ivan", "Ivanov", "Lenina Street, 5/3", "89634733435", "ivanov@ya.ru","test1"),true);
        }
        app.getNavigationHelper().gotoHomePage();
        List<RecordData> before = app.getRecordHelper().getRecordList();
        app.getRecordHelper().selectRecord(before.size()-1);
        app.getRecordHelper().deleteSelectedRecord();
        app.wd.switchTo().alert().accept();
        app.getNavigationHelper().gotoHomePage();

        List<RecordData> after = app.getRecordHelper().getRecordList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before,after);
        }
}
