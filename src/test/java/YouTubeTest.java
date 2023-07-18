import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class YouTubeTest {
    private WebDriver driver;
    private YouTubeHomePage homePage;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        homePage = new YouTubeHomePage(driver);
        driver.get("https://www.youtube.com/");
    }

    @Test
    public void testYouTubeSearchAndSubscribe() {
        Assert.assertEquals(driver.getTitle(), "YouTube");
        String searchQuery = generateRandomSearchQuery();
        homePage.enterSearchQuery(searchQuery);
        homePage.selectSearchDropdownItem(2);
        homePage.clickVideoResult(4);
        homePage.clickAvatarImage();
        homePage.clickSubscribeButton();
        Assert.assertTrue(homePage.isLoginTextDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private String generateRandomSearchQuery() {
        int min = 10;
        int max = 9999;
        int randomNum = min + (int)(Math.random() * ((max - min) + 1));
        return String.valueOf(randomNum);
    }
}
