package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RecordData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecordEmailTests extends TestBase {

    @Test
    public void testRecordEmails(){
        app.goTo().homePage();
        RecordData record = app.record().all().iterator().next();
        RecordData recordInfoFromEditForm = app.record().infoFromEditForm(record);

        assertThat(record.getAllemails(),equalTo(mergeEmail(recordInfoFromEditForm)));
    }

    private String mergeEmail(RecordData record) {
        return Arrays.asList(record.getEmail(), record.getEmail2(),record.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(RecordEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email){
        return email.replaceAll("\\s","");
    }
}
