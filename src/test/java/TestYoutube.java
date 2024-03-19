import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestYoutube {
    public static AppiumDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setUdid("R5CN70P4S8A");
        options.setAppPackage("com.google.android.youtube");
        options.setAppActivity("com.google.android.youtube.app.honeycomb.Shell$HomeActivity");
        options.setDeviceName("device");
        options.setAutomationName("UiAutomator2");
        options.setPlatformVersion("13");
        options.setAutoGrantPermissions(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(description = "Test to play my favorite music on Youtube")
    public void testPlayMyFavoriteMusicOnYouTube() throws InterruptedException {
        // Click on search icon
        Thread.sleep(10000);
        WebElement searchIcon = driver.findElement(AppiumBy.accessibilityId("Search"));
        searchIcon.click();

        // Send keys to search bar
        Thread.sleep(1000);
        WebElement searchBar = driver.findElement(AppiumBy.id("com.google.android.youtube:id/search_edit_text"));
        searchBar.sendKeys("Rick Rolled (Short Version)");

        // Click on search suggestion
        Thread.sleep(1000);
        String newXpath = "//android.widget.TextView[@text='rick rolled (short version)']";
        WebElement searchSuggestion = driver.findElement(AppiumBy.xpath(newXpath));
        searchSuggestion.click();

        // Click on search result
        Thread.sleep(2000);
        String titleXpath = "//android.view.ViewGroup[contains(@content-desc, 'Rick Rolled (Short Version)')]";
        WebElement searchResult = driver.findElement(AppiumBy.xpath(titleXpath));
        searchResult.click();

        // Click on expand video's description
        Thread.sleep(2000);
        String expandButtonXpath = "//android.view.ViewGroup[@content-desc='Expand description']/android.widget.ImageView";
        WebElement expandButton = driver.findElement(AppiumBy.xpath(expandButtonXpath));
        expandButton.click();

        // Assertion - assert that the uploader name Legacy PNDA is visible
        String uploaderNameXpath = "//android.view.ViewGroup[@content-desc='Legacy PNDA']";
        WebElement uploaderName = driver.findElement(AppiumBy.xpath(uploaderNameXpath));
        Boolean uploaderNameResult = uploaderName.isDisplayed();

        Assert.assertTrue(uploaderNameResult, "Verify if uploader name Legacy PNDA is visible");
    }
}