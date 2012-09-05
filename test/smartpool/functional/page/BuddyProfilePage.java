package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static junit.framework.Assert.assertEquals;


public class BuddyProfilePage extends Page{

    public BuddyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static final String BUDDY_PROFILE_NAME_ID = "myProfileName";
    @FindBy(how = How.ID, using = BUDDY_PROFILE_NAME_ID)
    private WebElement buddyProfileName;

       @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(BUDDY_PROFILE_NAME_ID));
    }

    public void verifyBuddyProfileDetails() {
        assertEquals("Ayush Tulsyan", buddyProfileName.getText());
    }
}