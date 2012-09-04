package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

    protected WebDriver webDriver;
    private WebDriverWait wait;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 15);
        waitForThePageToLoad();
        PageFactory.initElements(webDriver, this);
    }

    public abstract void waitForThePageToLoad();

    protected void waitForElementToLoad(final By by) {
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(by) != null;
            }
        });
    }
}
