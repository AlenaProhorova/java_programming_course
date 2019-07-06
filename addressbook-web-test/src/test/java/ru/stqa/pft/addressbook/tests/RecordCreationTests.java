package ru.stqa.pft.addressbook.tests;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RecordCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validRecords() throws IOException {
    File photo = new File("src/test/resources/avatar.png");
   // List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/records.xml")));
    String line = reader.readLine();
    String xml = "";
    while (line != null){
      xml += line;
     /* String[] split = line.split(";");
      list.add(new Object[] {new RecordData().withFirstname(split[0])
              .withLastname(split[1])
              .withHomePhone(split[2])
              .withAddress(split[3])
              .withEmail(split[4])
              .withGroup(split[5])
              .withPhoto(photo)});*/
      line = reader.readLine();
    }

    XStream xstream = new XStream();
    xstream.processAnnotations(RecordData.class);
    List<RecordData> records = (List<RecordData>) xstream.fromXML(xml);
    return records.stream().map((r) -> new Object[] {r}).collect(Collectors.toList()).iterator();

   // return list.iterator();
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
