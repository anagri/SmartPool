package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

    protected WebDriver webDriver;
    private WebDriverWait wait;
    protected String searchQuery;
    public static final String BASE_URL = "http://localhost:9090/smartpool";

    public Page(WebDriver webDriver) {
        this(webDriver, null);
    }

    public Page(WebDriver webDriver, String searchQuery) {
        this.searchQuery = searchQuery;
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 15);
        waitForThePageToLoad();
        PageFactory.initElements(webDriver, this);
    }

    public abstract void waitForThePageToLoad();

    public void select(String value) {
        WebElement webElement = webDriver.findElement(By.tagName("option"));
        webElement.sendKeys(value);
        webElement.sendKeys(Keys.ENTER);
    }

    protected void waitForElementToLoad(final By by) {
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(by) != null;
            }
        });
    }

    protected void waitForElementToLoad(final By by, final String text) {
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                WebElement element = webDriver.findElement(by);
                return element != null && element.getText().contains(text);
            }
        });
    }
}
