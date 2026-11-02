package com.testingAmar.pages;

import com.testingAmar.base.CommonToAllPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PracticeLocationInfoPage extends CommonToAllPage {

    WebDriver driver;

    public PracticeLocationInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    private By practiceLocationName =
            By.xpath("//input[@type='text' and @required]");
    private By practiceLocationBio =
            By.xpath("//body[@contenteditable='true']");
    By bioIframe =
            By.xpath("//iframe[contains(@class,'cke_wysiwyg_frame') and @title='Editor, editor1']");
    private By saveNextButton =
            By.xpath("//button[normalize-space()='Save & Next']");

    public void addPracticeLocationName(String pLName) {
        enterInput(practiceLocationName, pLName);
    }

    public void addPracticeLocationBio(String pLBio) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Switch to iframe
        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                        bioIframe
                )
        );

        // Step 2: Wait for editable body
        WebElement editor = wait.until(
                ExpectedConditions.elementToBeClickable(practiceLocationBio)
        );

        // Step 3: Enter text
        editor.click();
        editor.sendKeys(pLBio);

        // Step 4: Return to main page
        driver.switchTo().defaultContent();
    }
    public boolean isNextPageDisplayed() {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));
        clickElement(saveNextButton);
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        saveNextButton)
        ).isDisplayed();

    }
}
