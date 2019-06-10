package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.RecordData;

public class RecordCreationTests extends TestBase{

  @Test
  public void testRecordCreation() throws Exception {
  //  app.getNavigationHelper().gotoRecordPage();
    app.getRecordHelper().createRecord(new RecordData("Ivan", "Ivanov", "Lenina Street, 5/3", "89634733435", "ivanov@ya.ru","test1"),true);

    app.getSessionHelper().logout();
  }

}
