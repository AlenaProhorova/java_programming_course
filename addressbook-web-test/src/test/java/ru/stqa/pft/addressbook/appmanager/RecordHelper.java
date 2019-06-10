package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.RecordData;

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

    public void selectRecord() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedRecord() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void gotoRecordPage() {
        click(By.linkText("add new"));
    }

    public void initRecordModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitRecordModification() {
        click(By.name("update"));
    }

    public void createRecord(RecordData record, boolean codition) {
        gotoRecordPage();
        fillRecordForm(record, true);
        submitRecordCreation();
    }

    public boolean isThereARecord() {
        return isElementPresent(By.name("selected[]"));
    }
}

