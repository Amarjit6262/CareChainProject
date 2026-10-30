package com.testingAmar.driver;
import com.testingAmar.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
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
/*
                DevTools devTools =
                        ((HasDevTools) DriverManager.getDriver()).getDevTools();

                devTools.createSession();

                Map<String, Object> headers = new HashMap<>();
               headers.put("X-Mobile-App", "carechain-integration");

                devTools.send(Network.enable(
                        java.util.Optional.empty(),
                        java.util.Optional.empty(),
                        java.util.Optional.empty()
                ));

                devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
  */              break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
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
}