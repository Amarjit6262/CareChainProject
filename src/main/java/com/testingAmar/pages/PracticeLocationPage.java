package com.testingAmar.pages;

import com.testingAmar.base.CommonToAllPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PracticeLocationPage extends CommonToAllPage {
    WebDriver driver;

    public PracticeLocationPage(WebDriver driver){
        this.driver = driver;
    }
    private By profile = By.cssSelector("div[aria-label='Profile']");;

    public String practiceLocationDashboard(){
        String profileN = getText(profile);
        return profileN;
    }

    //public Str
}
