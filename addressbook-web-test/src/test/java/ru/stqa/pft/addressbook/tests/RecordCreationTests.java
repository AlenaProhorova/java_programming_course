package ru.stqa.pft.addressbook.tests;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RecordCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validRecordsFromXml() throws IOException {
    File photo = new File("src/test/resources/avatar.png");
   // List<Object[]> list = new ArrayList<Object[]>();
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/records.xml")))) {
      String line = reader.readLine();
      String xml = "";
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }

      XStream xstream = new XStream();
      xstream.processAnnotations(RecordData.class);
      List<RecordData> records = (List<RecordData>) xstream.fromXML(xml);
      return records.stream().map((r) -> new Object[]{r}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validRecordsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/records.json")))){
    String line = reader.readLine();
    String json = "";
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<RecordData> records = gson.fromJson(json, new TypeToken<List<RecordData>>() {}.getType());
    return records.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }
  }

  @Test  (dataProvider = "validRecordsFromJson") //(enabled = false)
  public void testRecordCreation(RecordData record) throws Exception {
   // File photo = new File("src/test/resources/avatar.png");
    app.goTo().homePage();
    Records before = app.db().records();
    app.record().create(record,true);
    app.goTo().homePage();
    assertThat(app.record().count(), equalTo(before.size()+1));
    Records after = app.db().records();
    assertThat(after,equalTo(
            before.withAdded(record.withId(after.stream().mapToInt((r) -> r.getId()).max().getAsInt()))));

    VerifyRecordListInUI();
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
