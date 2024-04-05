package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchContext extends BasePage {
    @AndroidFindBy(id = "search_edit_text")
    private WebElement searchInput;

    private WebElement foundButton;

    public SearchContext(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void searchByText(String searchText) {
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        this.searchInput.sendKeys(searchText);

        String xpathExpression = String.format("//android.widget.TextView[contains(@text, '%s')]", searchText.toLowerCase());
        foundButton = driver.findElement(AppiumBy.xpath(xpathExpression));
        wait.until(ExpectedConditions.elementToBeClickable(foundButton));
        foundButton.click();
    }
}
