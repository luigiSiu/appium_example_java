import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchContext;

import java.net.MalformedURLException;
import java.net.URL;

public class YoutubeTest {

    public static AppiumDriver driver;
    private HomePage homePage;

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

        homePage = new HomePage(driver);
    }

    @Test(description = "Test to play my favorite music on Youtube")
    public void testPlayMyFavoriteMusicOnYouTube() {
        String video = "Rick Rolled (Short Version)";

        SearchContext searchContext = homePage.clickOnSearchIcon();
        searchContext.searchByText(video);

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
