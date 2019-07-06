package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
        save(records,new File(file));
    }

    private static void save(List<RecordData> records, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (RecordData record : records){
            writer.write(String.format("%s;%s;%s;%s;%s\n", record.getFirstname(),
                    record.getLastname(),
                    record.getMobilePhone(),
                    record.getAddress(),
                    record.getEmail()));
        }
        writer.close();
    }

    private static List<RecordData> generateRecords(int count) {
        List<RecordData> records = new ArrayList<RecordData>();
        for (int i = 0; i<count; i++){
            records.add(new RecordData().withFirstname(String.format("FirstName %s", i))
                    .withLastname(String.format("LastName %s",i))
                    .withMobilePhone(String.format("%s%s%s%s%s%s%s%s",i,i,i,i,i,i,i,i))
                    .withAddress(String.format("Street %s, %s/%s",i,i,i))
                    .withEmail(String.format("test%s@tst.com",i)));
        }
        return records;
    }
}
