package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

     public static WebDriver getWebDriver(Browser browser) {
        WebDriver driver = null;
        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                driver =  WebDriverManager.firefoxdriver().create();
                break;
            case EDGE:
                driver =  WebDriverManager.edgedriver().create();
                break;
        }
        driver.manage().window().maximize();
        return driver;
    }

}
