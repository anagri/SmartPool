package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static junit.framework.Assert.assertEquals;

public class UserProfilePage extends Page{

    private static final String PROFILE_CLASS_NAME="header";

    @FindBy(how = How.CLASS_NAME, using = PROFILE_CLASS_NAME)
    private WebElement userProfile;

    public UserProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.className(PROFILE_CLASS_NAME));
    }

    public void verifyProfileDetails() {
        assertEquals("SmartPool Profile", userProfile.getText());
    }
}

