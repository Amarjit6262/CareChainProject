package com.testingAmar.driver;
import com.testingAmar.base.CommonToAllPage;
import com.testingAmar.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v137.network.Network;
import org.openqa.selenium.devtools.v137.network.model.Headers;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class DriverManager {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver = driver;
    }

    //initialize the driver
    public static void init() {
        String browser = PropertiesReader.readKey("browser");
        if (browser == null ) {
            throw new IllegalStateException(
                    "Browser is not configured in the properties file."
            );
        }
      /*  if (driver instanceof HasDevTools) {
            DevTools devTools = ((HasDevTools) driver).getDevTools();
            devTools.createSession();


        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Mobile-App", "carechain-integration");

        devTools.send(Network.enable(
                java.util.Optional.empty(),
                java.util.Optional.empty(),
                java.util.Optional.empty()
        ));

        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
        } else {
            System.out.println("DevTools not supported for: "
                    + driver.getClass().getSimpleName());
        }

*/
        browser = browser.toLowerCase().trim();

        switch (browser) {
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--guest");
                driver = new EdgeDriver(edgeOptions);
                break;
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);

        /*        DevTools devTools =
                        ((HasDevTools) DriverManager.getDriver()).getDevTools();

                devTools.createSession();

                Map<String, Object> headers = new HashMap<>();
               headers.put("X-Mobile-App", "carechain-integration");

                devTools.send(Network.enable(
                        java.util.Optional.empty(),
                        java.util.Optional.empty(),
                        java.util.Optional.empty()
                ));

                devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));*/
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                DevTools devTools1 =
                        ((HasDevTools) DriverManager.getDriver()).getDevTools();

                devTools1.createSession();

                Map<String, Object> headers1 = new HashMap<>();
                headers1.put("X-Mobile-App", "carechain-integration");

                devTools1.send(Network.enable(
                        java.util.Optional.empty(),
                        java.util.Optional.empty(),
                        java.util.Optional.empty()
                ));

                devTools1.send(Network.setExtraHTTPHeaders(new Headers(headers1)));
                break;
            default:
                throw new IllegalArgumentException(
                        "Browser not supported: " + browser
                );
        }
    }
//When close the browser

    public static void down() {
        if (getDriver() != null) {
            driver.quit();
            driver = null;
        }
    }

    public class PracticeLocationInfoPage extends CommonToAllPage {

        WebDriver driver;

        public PracticeLocationInfoPage(WebDriver driver){
            this.driver = driver;
        }

        private By practiceLocationName = By.xpath("//input[@type='text' and @required]");
        private By practiceLocationBio = By.xpath("//body[@contenteditable='true']");
        By bioIframe = By.xpath("//iframe[@title='Editor, editor2']");
        private By saveNextButton = By.xpath("//button[normalize-space()='Save & Next']");

        public void addPracticeLocationName(String pLName){
            enterInput(practiceLocationName,pLName);
        }

        public void addPracticeLocationBio(String pLBio){

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

        }
}