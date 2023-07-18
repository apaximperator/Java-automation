import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class YouTubeHomePage {
    private final WebDriver driver;

    private final By searchInput = By.name("search_query");
    private final By searchDropdown = By.xpath("//ul[@role='listbox']/li");
    private final By videoResults = By.cssSelector("div#dismissible.ytd-video-renderer");
    private final By avatarImage = By.cssSelector(".ytd-watch-metadata a.ytd-video-owner-renderer");
    private final By subscribeButton = By.cssSelector("#inner-header-container button[aria-label='Подписаться']");
    private final By loginText = By.cssSelector("a.yt-spec-button-shape-next--align-by-text[aria-label='Войти']");

    public YouTubeHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchQuery(String query) {
        WebElement searchInputField = driver.findElement(searchInput);
        searchInputField.clear();
        searchInputField.sendKeys(query);
    }

    public void selectSearchDropdownItem(int position) {
        WebElement searchInputField = driver.findElement(searchInput);
        searchInputField.sendKeys(Keys.ARROW_DOWN);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchDropdown));

        List<WebElement> dropdownItems = driver.findElements(searchDropdown);

        if (dropdownItems.size() >= position) {
            dropdownItems.get(position - 1).click();
        } else {
            throw new NoSuchElementException("No dropdown item found at position: " + position);
        }
    }
    public void clickVideoResult(int position) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> videoElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(videoResults));

        if (videoElements.size() >= position) {
            videoElements.get(position - 1).click();
        } else {
            throw new NoSuchElementException("No video found at position: " + position);
        }
    }

    public void clickAvatarImage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement avatarElement = wait.until(ExpectedConditions.visibilityOfElementLocated(avatarImage));
        avatarElement.click();
    }

    public void clickSubscribeButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement subscribeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(subscribeButton));
        subscribeElement.click();
    }

    public boolean isLoginTextDisplayed() {
        return driver.findElement(loginText).isDisplayed();
    }
}
