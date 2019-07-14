package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

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
        type(By.name("home"),recordData.getHomePhone());
        type(By.name("email"),recordData.getEmail());
        attach(By.name("photo"),recordData.getPhoto());

        if (creation) {
            if(recordData.getGroups().size()>0) {
                Assert.assertTrue(recordData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(recordData
                        .getGroups().iterator().next().getName());
            }
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
        recordCache = null;
    }

    public void modify(RecordData record) {
       selectRecordById(record.getId());
       initRecordModification(record.getId());
       fillRecordForm(record,false);
       submitRecordModification();
       recordCache = null;
    }

    public void delete(int index) {
        selectRecord(index);
        deleteSelectedRecord();
    }

    public void delete(RecordData record) {
        selectRecordById(record.getId());
        deleteSelectedRecord();
        recordCache = null;
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

    private Records recordCache = null;

    public Records all() {
        if (recordCache != null){
            return new Records(recordCache);
        }
        recordCache = new Records();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element: elements){
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
           // String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
           /* String[] phones = element.findElement(By.xpath(".//td[6]")).getText().split("\n");
            recordCache.add(new RecordData()
                    .withId(id)
                    .withFirstname(firstName)
                    .withLastname(lastName)
                    .withHomePhone(phones[0])
                    .withMobilePhone(phones[1])
                    .withWorkPhone(phones[2]));*/
           String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            String allAddress = element.findElement(By.xpath(".//td[4]")).getText();
            recordCache.add(new RecordData()
                    .withId(id)
                    .withFirstname(firstName)
                    .withLastname(lastName)
                    .withAllphones(allPhones)
                    .withAllemails(allEmails)
                    .withAlladdress(allAddress));
        }
        return new Records(recordCache);
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public RecordData infoFromEditForm(RecordData record) {
        initRecordModificationById(record.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");

        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        String address = wd.findElement(By.name("address")).getText();
        String address2 = wd.findElement(By.name("address2")).getText();
        wd.navigate().back();
        return new RecordData().withId(record.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withWorkPhone(work)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3)
                .withAddress(address)
                .withAddress2(address2);

    }

    private void initRecordModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        //wd.findElement(By.cssSelector(String.format("//input[value='%s']/../../td[8]/a",id)));
        //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }
}

