package smartpool.functional.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static junit.framework.Assert.assertEquals;

public class ViewProfilePage extends Page {

    public static final String PROFILE_NAME_ID = "profileName";
    public static final String PROFILE_PHONE_NUMBER_ID = "profilePhoneNumber";
    public static final String PROFILE_EMAIL_ID = "profileEmail";
    public static final String PROFILE_BUTTON_ID = "profileButton";

    @FindBy(how = How.ID, using = PROFILE_NAME_ID)
    private WebElement profileName;

    @FindBy(how = How.ID, using = PROFILE_PHONE_NUMBER_ID)
    private WebElement profilePhoneNumber;

    @FindBy(how = How.ID, using = PROFILE_EMAIL_ID)
    private WebElement profileEmail;

    @FindBy(how = How.ID, using = PROFILE_BUTTON_ID)
    private WebElement profileButton;

    public ViewProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(PROFILE_NAME_ID));
    }

    public String getProfileName() {
        return profileName.getText();
    }

    public String getProfilePhoneNumber() {
        return profilePhoneNumber.getText();
    }

    public String getProfileEmail() {
        return profileEmail.getText();
    }

    public String getProfileButton() {
        return profileButton.getText();
    }

    public void verifyDetailsOfNewBuddy(String buddyUserName) {
        assertEquals(buddyUserName, profileName.getText());
    }
}
