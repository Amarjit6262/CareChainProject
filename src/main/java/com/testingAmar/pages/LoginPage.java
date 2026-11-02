package com.testingAmar.pages;

import com.testingAmar.base.CommonToAllPage;
import com.testingAmar.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.testingAmar.driver.DriverManager.getDriver;

public class LoginPage extends CommonToAllPage {

    WebDriver driver;

   public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private  By username =
            By.cssSelector("input[type='mail']");
    private  By password =
            By.cssSelector("input[type='password']");
    private  By loginButton =
            By.xpath("//button[@type='submit']");
    By error_message =
            By.xpath("//div[@role='alert' and contains(.,'User name or password is incorrect')]");

    public void loginWithValidCredentials(String user, String pwd) {

        openUrl();
        enterInput(username,user);
        enterInput(password,pwd);
        clickElement(loginButton);
        WaitHelper.waitJVM(5000);
        // Wait - Thread sleep
    }

    public String loginWithInValidCredentials(String user, String pwd) {

        openUrl();
        enterInput(username,user);
        enterInput(password,pwd);
        clickElement(loginButton);
        WaitHelper.checkVisibility(getDriver(),error_message);
        return getText(error_message);
        // Wait - Thread sleep
    }



}
