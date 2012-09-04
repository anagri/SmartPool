package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static junit.framework.Assert.assertEquals;

public class HomePage extends Page {

    public static final String WELCOME_MESSAGE_ID = "welcomeMessage";
    @FindBy(how = How.ID, using = WELCOME_MESSAGE_ID)
    private WebElement welcomeMessage;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(WELCOME_MESSAGE_ID));
    }

    public void verifyWelcomeMessagePresent() {
        assertEquals("Welcome to SmartPool!", welcomeMessage.getText());
    }
}
