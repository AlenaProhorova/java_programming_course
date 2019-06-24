package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.RecordData;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteSelectedRecord() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void gotoRecordPage() {
        click(By.linkText("add new"));
    }

    public void initRecordModification(int index) {
        wd.findElements(By.xpath(".//img[@alt='Edit']")).get(index).click();
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

    public void modify(int index, RecordData record) {
       selectRecord(index);
       initRecordModification(index);
       fillRecordForm(record,false);
       submitRecordModification();
    }

    public void delete(int index) {
        selectRecord(index);
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
}

