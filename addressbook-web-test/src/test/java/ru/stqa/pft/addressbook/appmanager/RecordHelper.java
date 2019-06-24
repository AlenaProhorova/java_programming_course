package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecordHelper extends HelperBase {
  // public WebDriver wd;

    public RecordHelper(WebDriver wd) {
        super(wd);
    }

    public void fillRecordForm(RecordData recordData, boolean creation) {
        type(By.name("firstname"),recordData.getFirstname());
        type(By.name("lastname"),recordData.getLastname());
        type(By.name("address"),recordData.getAddress());
        type(By.name("home"),recordData.getHometelefon());
        type(By.name("email"),recordData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(recordData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitRecordCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectRecord( int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectRecordById( int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void deleteSelectedRecord() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void gotoRecordPage() {
        click(By.linkText("add new"));
    }

    public void initRecordModification(int id) {
      //  wd.findElement(By.cssSelector("input[@id='"+ id + "']")) .click();
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id +"']")).click();
       // wd.findElement(By.cssSelector("input[@id='"+ id + "']")).getText().click();
       // wd.findElements(By.xpath(".//img[@alt='Edit']")).get(id).click();
       // click(By.xpath(".//img[@alt='Edit']"));
    }

    public void submitRecordModification() {
        click(By.name("update"));
    }

    public void create(RecordData record, boolean codition) {
        gotoRecordPage();
        fillRecordForm(record, true);
        submitRecordCreation();
    }

    public void modify(RecordData record) {
       selectRecordById(record.getId());
       initRecordModification(record.getId());
       fillRecordForm(record,false);
       submitRecordModification();
    }

    public void delete(int index) {
        selectRecord(index);
        deleteSelectedRecord();
    }

    public void delete(RecordData record) {
        selectRecordById(record.getId());
        deleteSelectedRecord();
    }

    public boolean isThereARecord() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<RecordData> list() {
        List<RecordData> records= new ArrayList<RecordData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element: elements){
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            records.add(new RecordData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return records;
    }

    public Records all() {
        Records records= new Records();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element: elements){
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            records.add(new RecordData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return records;
    }
}

