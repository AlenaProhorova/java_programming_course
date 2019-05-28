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
}
