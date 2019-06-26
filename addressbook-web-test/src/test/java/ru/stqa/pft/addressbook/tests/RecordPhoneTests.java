package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RecordData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecordPhoneTests extends TestBase{

    @Test
    public void testRecordPhones(){
        app.goTo().homePage();
        RecordData record = app.record().all().iterator().next();
        RecordData recordInfoFromEditForm = app.record().infoFromEditForm(record);


     /*   assertThat(record.getHomePhone(),equalTo(cleaned(recordInfoFromEditForm.getHomePhone())));
        assertThat(record.getMobilePhone(),equalTo(cleaned(recordInfoFromEditForm.getMobilePhone())));
        assertThat(record.getWorkPhone(),equalTo(cleaned(recordInfoFromEditForm.getWorkPhone())));*/

        assertThat(record.getAllphones(),equalTo(mergePhones(recordInfoFromEditForm)));
    }

    private String mergePhones(RecordData record) {
       return Arrays.asList(record.getHomePhone(), record.getMobilePhone(),record.getWorkPhone())
        .stream().filter((s) -> !s.equals(""))
               .map(RecordPhoneTests::cleaned)
               .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

}
