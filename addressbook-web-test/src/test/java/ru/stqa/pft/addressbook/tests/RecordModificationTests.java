package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RecordData;

public class RecordModificationTests extends TestBase{

    @Test
    public void testRecordModification() {
        if (! app.getRecordHelper().isThereARecord()){
            app.getRecordHelper().createRecord(new RecordData("Ivan", "Ivanov", "Lenina Street, 5/3", "89634733435", "ivanov@ya.ru","test1"),true);
        }
        app.getNavigationHelper().gotoHomePage();
        app.getRecordHelper().selectRecord();
        app.getRecordHelper().initRecordModification();
        app.getRecordHelper().fillRecordForm(new RecordData("Ivan", "Ivanov", "Lenina Street, 5/3", "89634733435", "ivanov@ya.ru",null),false);
        app.getRecordHelper().submitRecordModification();
    }
}
