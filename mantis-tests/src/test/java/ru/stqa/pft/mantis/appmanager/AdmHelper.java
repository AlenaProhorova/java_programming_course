package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class AdmHelper extends HelperBase {

    public  AdmHelper(ApplicationManager app){
        super(app);
    }


    public void login(String username, String password){
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"),username);
        click(By.cssSelector("input[value='Login']"));
        type(By.name("password"),password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void resetPassword(int id, String username) throws InterruptedException {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        wd.findElement(By.linkText(username)).click();
        sleep(2000);
       // wd.findElement(By.cssSelector("a[href='http://localhost/mantisbt-2.21.1/manage_user_edit_page.php?user_id='" + id +"']")).click();
        click(By.cssSelector("input[value='Reset Password']"));

    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
