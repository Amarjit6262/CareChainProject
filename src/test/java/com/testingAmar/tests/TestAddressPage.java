package com.testingAmar.tests;

import com.testingAmar.driver.DriverManager;
import com.testingAmar.pages.AddressPage;
import com.testingAmar.pages.LoginPage;
import com.testingAmar.pages.PracticeLocationInfoPage;
import com.testingAmar.pages.PracticeLocationPage;
import com.testingAmar.utils.PropertiesReader;
import com.testingAmar.utils.WaitHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAddressPage {
    @BeforeMethod
    public void setUp() {
        DriverManager.init();
    }

    // @AfterMethod
    public void tearDown() {
        //  DriverManager.down();
    }


    @Test
    public void testEnterAddressDetailsSuccessfully(){

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.loginWithValidCredentials(PropertiesReader.readKey("username"), PropertiesReader.readKey("password"));
        Assert.assertNotNull(PropertiesReader.readKey("username"), "Username is Empty");
        WaitHelper.waitJVM(15000);
        PracticeLocationPage practiceLocationPage = new PracticeLocationPage(DriverManager.getDriver());
        practiceLocationPage.clickAddButton();
        PracticeLocationInfoPage practiceLocationInfoPage = new PracticeLocationInfoPage(DriverManager.getDriver());
        practiceLocationInfoPage.addPracticeLocationName(PropertiesReader.readKey("practiceLocationName"));
        practiceLocationInfoPage.addPracticeLocationBio(PropertiesReader.readKey("practiceLocationBio"));
        practiceLocationInfoPage.isNextPageDisplayed();
        Assert.assertNotNull(PropertiesReader.readKey("practiceLocationName"),"Location Name is Empty");
        Assert.assertNotNull(PropertiesReader.readKey("practiceLocationBio"),"Location Bio is Empty");
        Assert.assertTrue(
                practiceLocationInfoPage.isNextPageDisplayed(),
                "Next page is not displayed after clicking Save & Next"
        );

        AddressPage addressPage = new AddressPage(DriverManager.getDriver());
        addressPage.enterPracticeLocationAddress(PropertiesReader.readKey("Address"));
        addressPage.enterPracticeLocationPhone(PropertiesReader.readKey("phone"));
        addressPage.enterPracticeLocationFax(PropertiesReader.readKey("Fax"));
        String expectedFax =
                PropertiesReader.readKey("practiceLocationFax");

        Assert.assertNotNull(expectedFax, "Fax number is null");

      //  Assert.enterPracticeLocationFax(expectedFax);

        Assert.assertEquals(
                PropertiesReader.readKey("Fax"),
                expectedFax,
                "Practice Location Fax Number does not match!"
        );
    }

}
