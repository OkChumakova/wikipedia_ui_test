package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void checkBrokenLinks(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
        List<WebElement> links = driver.findElements(By.xpath("//a[@href]"));
        SoftAssert assertion = new SoftAssert();
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            int respCode = 0;
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

                conn.setRequestMethod("HEAD");
                conn.connect();
                respCode = conn.getResponseCode();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ;

            assertion.assertTrue(respCode < 400
                    , "The link with Text: " + link.getText() + " is broken with code " + respCode);
        }
        assertion.assertAll();
    }

}
