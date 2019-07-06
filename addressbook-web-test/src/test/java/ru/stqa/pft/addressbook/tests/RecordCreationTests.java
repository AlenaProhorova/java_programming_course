package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RecordCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validRecords(){
    File photo = new File("src/test/resources/avatar.png");
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new RecordData().withFirstname("Ivan")
            .withLastname("Ivanov")
            .withAddress("Lenina Street, 5/3")
            .withHomePhone("89634733435")
            .withEmail("ivanov@ya.ru")
            .withGroup("test0")
            .withPhoto(photo)});
    list.add(new Object[] {new RecordData().withFirstname("Petr")
            .withLastname("Petrov")
            .withAddress("Lenina Street, 5/3")
            .withHomePhone("89634733435")
            .withEmail("petrov@ya.ru")
            .withGroup("test0")
            .withPhoto(photo)});
    list.add(new Object[] {new RecordData().withFirstname("Sidr")
            .withLastname("Sidorov")
            .withAddress("Lenina Street, 5/3")
            .withHomePhone("89634733435")
            .withEmail("sidorov@ya.ru")
            .withGroup("test0")
            .withPhoto(photo)});
    return list.iterator();
  }

  @Test  (dataProvider = "validRecords") //(enabled = false)
  public void testRecordCreation(RecordData record) throws Exception {
   // File photo = new File("src/test/resources/avatar.png");
    app.goTo().homePage();
    Records before = app.record().all();
  /*  RecordData record = new RecordData()
            .withFirstname("Ivan")
            .withLastname("Ivanov")
            .withAddress("Lenina Street, 5/3")
            .withHomePhone("89634733435")
            .withEmail("ivanov@ya.ru")
            .withGroup("test0")
            .withPhoto(photo);*/
    app.record().create(record,true);
    app.goTo().homePage();
    assertThat(app.record().count(), equalTo(before.size()+1));
    Records after = app.record().all();
    assertThat(after,equalTo(
            before.withAdded(record.withId(after.stream().mapToInt((r) -> r.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir(){
    File currentDir = new File (".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/avatar.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
