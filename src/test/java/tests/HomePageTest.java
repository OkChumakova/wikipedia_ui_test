package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.IDriverFactory;
import utils.IExcelPoiParser;
import utils.IPropertyReader;


public class HomePageTest extends BaseTest {

    @Test
    public void checkTabTitle(ITestContext context) {
        WebDriver driver = IDriverFactory.getWebDriver(IPropertyReader.getBrowser());
        try{
            context.setAttribute("WebDriver", driver);
            HomePage homePage = new HomePage(driver);
            homePage.assertTabTitle("Wikipedia, the free encyclopedia");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void checkWelcomeText(ITestContext context) {
        WebDriver driver = IDriverFactory.getWebDriver(IPropertyReader.getBrowser());
        try{
            context.setAttribute("WebDriver", driver);
            HomePage homePage = new HomePage(driver);
            homePage.assertTextIsPresent("Welcome to Wikipedia,");
        } finally {
            driver.quit();
        }
    }

    @Test(dataProvider = "getDataForLanguageSwitch")
    public void checkLanguageSwitch(String language, String value, ITestContext context) {
        WebDriver driver = IDriverFactory.getWebDriver(IPropertyReader.getBrowser());
        try {
            context.setAttribute("WebDriver", driver);
            HomePage homePage = new HomePage(driver);
            homePage.checkLanguageSwitching(driver, language, value);
        } finally {
            driver.quit();
        }
    }

    @Test(dataProvider = "getDataForSearch")
    public void checkSearch(String searchValue, ITestContext context) {
        WebDriver driver = IDriverFactory.getWebDriver(IPropertyReader.getBrowser());
        context.setAttribute("WebDriver", driver);
        HomePage homePage = new HomePage(driver);
        homePage.clickSearchInputField()
                .enterKeysIntoSearchField(searchValue)
                .assertSearchResults(searchValue);
        driver.quit();
    }

//    @Test
//    public void checkLinks(ITestContext context) {
//        WebDriver driver = IDriverFactory.getWebDriver(IPropertyReader.getBrowser());
//        context.setAttribute("WebDriver", driver);
//        HomePage homePage = new HomePage(driver);
//        homePage.checkBrokenLinks(driver, IPropertyReader.getUrl());
//        driver.quit();
//    }

    @DataProvider
    public Object[][] getDataForLanguageSwitch() {
        return IExcelPoiParser.getData();
    }

    @DataProvider
    public Object[][] getDataForSearch() {
        Object[][] data = new Object[3][1];
        data[0][0] = "for";
        data[1][0] = "tab";
        data[2][0] = "low";
        System.out.println(data);
        return data;
    }
}
