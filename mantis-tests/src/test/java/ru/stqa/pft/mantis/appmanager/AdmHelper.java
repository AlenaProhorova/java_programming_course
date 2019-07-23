package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdmHelper extends HelperBase {

    public AdmHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password){
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"),username);
        click(By.cssSelector("input[value='Login']"));
        type(By.name("password"),password);
        click(By.cssSelector("input[value='Login']"));
    }

}
