package com.testingAmar.tests;

import com.testingAmar.driver.DriverManager;
import com.testingAmar.pages.LoginPage;
import com.testingAmar.pages.PracticeLocationPage;
import com.testingAmar.utils.PropertiesReader;
import com.testingAmar.utils.WaitHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPracticeLocationPage {

    @BeforeMethod
    public void setUp() {
        DriverManager.init();
    }

    // @AfterMethod
    public void tearDown() {
        //  DriverManager.down();
    }
    @Test
    public void testAddButton(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.loginWithValidCredentials(PropertiesReader.readKey("username"), PropertiesReader.readKey("password"));
        Assert.assertNotNull(PropertiesReader.readKey("username"), "Username is Empty");
        WaitHelper.waitJVM(15000);
        PracticeLocationPage practiceLocationPage = new PracticeLocationPage(DriverManager.getDriver());
        practiceLocationPage.clickAddButton();

    }
}
