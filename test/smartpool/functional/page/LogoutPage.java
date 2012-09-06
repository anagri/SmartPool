package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;

public class LogoutPage extends Page {

    @FindBy(how = How.ID, using = "msg")
    private WebElement logoutMessage;

    private final String APP_NAME = "app-name";

    public LogoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(APP_NAME));
    }


    public void verifySuccessMessage() {
        assertTrue(logoutMessage.getText().contains("Logout successful"));
    }

    public boolean askLoginWhenGoToHomePage() throws MalformedURLException {
        webDriver.get(BASE_URL);
        if (webDriver.findElement(By.id("login")) != null) {
            return true;
        }
        return false;
    }
}
