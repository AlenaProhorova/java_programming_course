package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RecordData;

import java.util.Comparator;
import java.util.List;

public class RecordModificationTests extends TestBase{

    @Test
    public void testRecordModification() {
        if (! app.getRecordHelper().isThereARecord()){
            app.getRecordHelper().createRecord(new RecordData("Ivan", "Ivanov", "Lenina Street, 5/3", "89634733435", "ivanov@ya.ru","test1"),true);
        }
        app.getNavigationHelper().gotoHomePage();
        List<RecordData> before = app.getRecordHelper().getRecordList();
        app.getRecordHelper().selectRecord(before.size()-1);
        app.getRecordHelper().initRecordModification(before.size()-1);
        RecordData record = new RecordData(before.get(before.size() - 1).getId(),"Nikita", "Nikitov", "Lenina Street, 5/3", "89634733435", "ivanov@ya.ru",null);
        app.getRecordHelper().fillRecordForm(record,false);
        app.getRecordHelper().submitRecordModification();
        app.getNavigationHelper().gotoHomePage();
        List<RecordData> after = app.getRecordHelper().getRecordList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(record);

        Comparator<? super RecordData> byId = (r1, r2) ->  Integer.compare(r1.getId(), r2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
