package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.RecordData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class RecordDataGenerator {

    @Parameter(names = "-c", description = "Record count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static  void main (String[] args) throws IOException {

        RecordDataGenerator generator = new RecordDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<RecordData> records = generateRecords(count);

        if (format.equals("csv")){
            saveAsCsv(records,new File(file));
        }else if (format.equals("xml")){
            saveAsXml(records,new File(file));
        } else if (format.equals("json")) {
            saveAsJson(records, new File(file));
        } else{
            System.out.println("Unrecognized format"+ format);
        }
    }

    private static void saveAsCsv(List<RecordData> records, File file) throws IOException {
       try(Writer writer = new FileWriter(file)) {
           for (RecordData record : records) {
               writer.write(String.format("%s;%s;%s;%s;%s;%s;$s\n", record.getFirstname(),
                       record.getLastname(),
                       record.getHomePhone(),
                       record.getAddress(),
                       record.getEmail(),
                      // record.getGroup(),
                       record.getPhoto()));
           }
       }
    }

    private void saveAsJson(List<RecordData> records, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(records);
        try(Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<RecordData> records, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(RecordData.class);
        xstream.alias("record", RecordData.class);
        String xml = xstream.toXML(records);
        try(Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private static List<RecordData> generateRecords(int count) {
        File photo = new File("src/test/resources/avatar.png");
        List<RecordData> records = new ArrayList<RecordData>();
        for (int i = 0; i<count; i++){
            records.add(new RecordData().withFirstname(String.format("FirstName %s", i))
                    .withLastname(String.format("LastName %s",i))
                    .withHomePhone(String.format("%s%s%s%s%s%s%s%s",i,i,i,i,i,i,i,i))
                    .withAddress(String.format("Street %s, %s/%s",i,i,i))
                    .withEmail(String.format("test%s@tst.com",i))
                 //   .withGroup(String.format("test0"))
                    .withPhoto(photo));
        }
        return records;
    }
}
