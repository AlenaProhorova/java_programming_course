package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.TestException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.RecordData;
import ru.stqa.pft.addressbook.model.Records;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class TestBase {

    Logger log = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    // protected WebDriver wd;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod (alwaysRun = true)
    public void logTestStart(Method m, Object[] p) {
        log.info("Start test " + m.getName() + " with parameters" + Arrays.asList(p));
    }

    @AfterMethod (alwaysRun = true)
    public void logTestStop(Method m, Object[] p) {
        log.info("Stop test " + m.getName());
    }

    public void VerifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData()
                            .withId(g.getId())
                            .withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void VerifyRecordListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Records dbRecords = app.db().records();
            Records uiRecords = app.record().all();
            assertThat(uiRecords, equalTo(dbRecords.stream()
                    .map((r) -> new RecordData()
                            .withFirstname(r.getFirstname()))
                    .collect(Collectors.toSet())));
        }
    }
}
