package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.RecordData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecordAddressTests extends TestBase {

    @Test
    public void testRecordAddress(){
        app.goTo().homePage();
        RecordData record = app.record().all().iterator().next();
        RecordData recordInfoFromEditForm = app.record().infoFromEditForm(record);

        assertThat(record.getAlladdress(),equalTo(mergeAddress(recordInfoFromEditForm)));
    }

    private String mergeAddress(RecordData record) {
        return Arrays.asList(record.getAddress())
                .stream().filter((s) -> !s.equals(""))
               // .map(RecordAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

 /*   public static String cleaned(String address){
        return address.replaceAll("\\s","");
    }*/
}

