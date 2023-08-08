package com.webstaurant.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;

public class Driver {

    private Driver(){}


    private static WebDriver driver;


    public static WebDriver getDriver(){

        String browserType = ConfigurationReader.getProperty("browser");

        switch (browserType){
            case "chrome":
                if (driver == null) {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    DesiredCapabilities cp = new DesiredCapabilities();
                    cp.setCapability(ChromeOptions.CAPABILITY, options);
                    options.merge(cp);
                    driver = new ChromeDriver(options);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    driver.manage().window().maximize();
                }
                return driver;
            default:
                throw new IllegalArgumentException("Sorry, only Chrome available now");
        }

    }


    public static void closeDriver() {
        if (driver != null) {

            driver.quit();

            driver = null;
        }

    }

    public static void navigateBack() {

        driver.navigate().back();

    }
}
