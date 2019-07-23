package ru.stqa.pft.mantis.tests;

import org.apache.http.client.methods.HttpPost;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase{

    private SessionFactory sessionFactory;


    @BeforeClass
    public void setBD() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test
    public void testChangePassword() throws InterruptedException, IOException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData where id is not 1" ).list();
        /*for ( UserData userData : result ) {

            System.out.println(userData);
        }*/
        session.getTransaction().commit();
        session.close();
       UserData changeUser = result.iterator().next();
        app.mail().start();
           changeUser.getId();
           changeUser.getUsername();
           changeUser.getEmail();
        app.admhelp().login("administrator","root");
        app.admhelp().resetPassword(changeUser.getId(),changeUser.getUsername());
        List<MailMessage> mailMessages =  app.mail().waitForMail(1, 20000);
        String confirmationLink = findConfirmationLink(mailMessages, changeUser.getEmail());
        app.admhelp().finish(confirmationLink, "12345");
        assertTrue(app.newSession().login( changeUser.getUsername(), "12345"));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true) //comment
    public void stopMailServer(){
        app.mail().stop();
    }
}
