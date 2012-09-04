package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import smartpool.functional.HomePageTest;

public class LoginPage extends Page {

    public static final String USERNAME_ID = "username";
    public static final String TEST_USERNAME = "test.twu";
    public static final String TEST_PASSWORD = "Th0ughtW0rks@12";

    @FindBy(how = How.ID, using = USERNAME_ID)
    private WebElement userName;

    @FindBy(how = How.ID, using = "password")
    private WebElement password;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(USERNAME_ID));
    }

    public HomePage login() {
        return login(TEST_USERNAME, TEST_PASSWORD);
    }

    public HomePage login(String userNameText, String passwordText) {
        userName.sendKeys(userNameText);
        password.sendKeys(passwordText);
        userName.submit();
        return new HomePage(webDriver);
    }
}
