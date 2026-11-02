package com.testingAmar.pages;

import com.testingAmar.base.CommonToAllPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddressPage extends CommonToAllPage {

    WebDriver driver;

    public AddressPage(WebDriver driver){
        this.driver = driver;
    }

    By practiceLocationAddress =
            By.xpath("//input[@placeholder='Enter a location']");
    By practiceLocationPhone =
            By.xpath("//input[@type='tel']");
    By practiceLocationFax =
            By.xpath("//label[normalize-space()='Fax']/following-sibling::div//input");

    public void enterPracticeLocationAddress(String address) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(practiceLocationAddress)
        );

        element.clear();
        element.sendKeys(address);
    }

    public void enterPracticeLocationPhone(String phone) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement phoneInput = wait.until(
                ExpectedConditions.elementToBeClickable(practiceLocationPhone)
        );

        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public String getPracticeLocationPhone() {
        return driver.findElement(practiceLocationPhone)
                .getDomProperty("value");
    }

    public void enterPracticeLocationFax(String fax) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement faxInput = wait.until(
                ExpectedConditions.elementToBeClickable(practiceLocationFax)
        );

        faxInput.clear();
        faxInput.sendKeys(fax);
    }

    public String getPracticeLocationFax() {
        return driver.findElement(practiceLocationFax)
                .getDomProperty("value");
    }
}
