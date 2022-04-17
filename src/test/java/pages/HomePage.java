package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.IPropertyReader;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(IPropertyReader.getUrl());
    }

    @FindBy(css = "#p-lang span.vector-menu-heading-label")
    private WebElement staticHeaderTextForLanguagesList;

    @FindBy(id = "searchInput")
    private WebElement searchInputField;

    @FindBy(xpath = "//div[@id='mp-topbanner' and @class='mp-bordered']")
    private WebElement topWelcomeBanner;

    public HomePage assertTabTitle(String titleName) {
        Assert.assertEquals(driver.getTitle(), titleName);
        return this;
    }

    public HomePage assertTextIsPresent(String text) {
        Assert.assertEquals(topWelcomeBanner.getText(), text);
        return this;
    }

    public HomePage clickSearchInputField() {
        searchInputField.click();
        return this;
    }

    public HomePage enterKeysIntoSearchField(String keys) {
        searchInputField.sendKeys(keys);
        return this;
    }

    public HomePage assertSearchResults(String searchKey) {
        List<WebElement> listOfSuggestedResults = driver.findElements(By.className("suggestions-result"));
        for (WebElement webElement : listOfSuggestedResults) {
            String textOfElement = webElement.getText();
            Assert.assertTrue(textOfElement.toLowerCase().startsWith(searchKey.toLowerCase()),
                    "The search with search key: " + searchKey + " is incorrect!");
        }
        return this;
    }

    public HomePage checkLanguageSwitching(WebDriver driver, String language, String value) {
        driver.findElement(By.linkText(language)).click();
        String text = staticHeaderTextForLanguagesList.getText();
        Assert.assertEquals(text, value, "The language is not switched to: " + language);
        return this;
    }

}
