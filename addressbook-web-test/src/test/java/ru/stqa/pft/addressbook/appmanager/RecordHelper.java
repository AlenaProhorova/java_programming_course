package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.RecordData;

public class RecordHelper extends HelperBase {
   private WebDriver wd;

    public RecordHelper(WebDriver wd) {
        super(wd);
    }

    public void fillRecordForm(RecordData recordData) {
        type(By.name("firstname"),recordData.getFirstname());
        type(By.name("lastname"),recordData.getLastname());
        type(By.name("address"),recordData.getAddress());
        type(By.name("home"),recordData.getHometelefon());
        type(By.name("email"),recordData.getEmail());
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


    public void initRecordModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitRecordModification() {
        click(By.name("update"));
    }
}

