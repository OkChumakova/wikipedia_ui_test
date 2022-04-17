package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.HomePage;
import utils.DriverFactory;
import utils.ExcelPoiParser;
import utils.PropertyReader;


public class HomePageTest extends BaseTest {

    WebDriver driver;
    HomePage homePage;

    @Test
    public void checkTabTitle() {
            homePage.assertTabTitle("Wikipedia, the free encyclopedia");
    }

    @Test
    public void checkWelcomeText() {
            homePage.assertTextIsPresent("Welcome to Wikipedia,");
    }

    @Test(dataProvider = "getDataForLanguageSwitch")
    public void checkLanguageSwitch(String language, String value) {
            homePage.checkLanguageSwitching(driver, language, value);
    }

    @Test(dataProvider = "getDataForSearch")
    public void checkSearch(String searchValue) {
            homePage.clickSearchInputField()
                    .enterKeysIntoSearchField(searchValue)
                    .assertSearchResults(searchValue);
    }

    @Test
    public void checkLinks() {
        homePage.checkBrokenLinks(driver, PropertyReader.getUrl());
    }


    @BeforeMethod
    public void BeforeMethod(ITestContext context) {
        this.driver =  DriverFactory.getWebDriver(PropertyReader.getBrowser());
        context.setAttribute("WebDriver", driver);
        this.homePage = new HomePage(driver);
    }

    @AfterMethod
    public void AfterMethod() {
        this.driver.quit();
    }

    @DataProvider
    public Object[][] getDataForLanguageSwitch() {
        return ExcelPoiParser.getData();
    }

    @DataProvider
    public Object[][] getDataForSearch() {
        Object[][] data = new Object[3][1];
        data[0][0] = "for";
        data[1][0] = "tab";
        data[2][0] = "low";
        return data;
    }
}
