package com.testingAmar.tests;

import com.testingAmar.driver.DriverManager;
import com.testingAmar.pages.LoginPage;
import com.testingAmar.pages.PracticeLocationPage;
import com.testingAmar.utils.PropertiesReader;
import com.testingAmar.utils.WaitHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLoginSuccessfully {
    @BeforeMethod
    public void setUp() {
        DriverManager.init();
    }

   // @AfterMethod
    public void tearDown() {
      //  DriverManager.down();
    }

    @Owner("Amarjit")
    @Description("Verify that with valid email, pass,user able to login successfully")
    @Test
    public void testLoginWithValidCredentials() throws InterruptedException {

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.loginWithValidCredentials(PropertiesReader.readKey("username"), PropertiesReader.readKey("password"));
        Assert.assertNotNull(PropertiesReader.readKey("username"), "Username is Empty");
        WaitHelper.waitJVM(15000);
        PracticeLocationPage practiceLocationPage = new PracticeLocationPage(DriverManager.getDriver());
        String profile = practiceLocationPage.practiceLocationDashboard();
        Assert.assertEquals(PropertiesReader.readKey("profile_name"), profile);
    }

    @Owner("Amarjit")
    @Description("Verify that with invalid email, pass, error message is shown on the app")
    @Test
    public void testLoginWithInValidCredentials() throws InterruptedException {

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        String err_message = loginPage.loginWithInValidCredentials(PropertiesReader.readKey("invalid_username"), PropertiesReader.readKey("password"));
        Assert.assertNotNull(PropertiesReader.readKey("error_message"), err_message);
    }
}