package ru.stqa.pft.mantis.tests;

import org.apache.http.client.methods.HttpPost;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

public class ChangePasswordTest extends TestBase{

    private SessionFactory sessionFactory;

    @BeforeClass
    public void setUp() throws Exception {
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
    public void testChangePassword(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData where id is not 1" ).list();
        /*for ( UserData userData : result ) {

            System.out.println(userData);
        }*/
        session.getTransaction().commit();
        session.close();

       UserData changeUser = result.iterator().next();
           changeUser.getId();
           changeUser.getUsername();
           changeUser.getEmail();

        app.admHelper().login("administrator","root");
    }

}
