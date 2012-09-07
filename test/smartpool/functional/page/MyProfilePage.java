package smartpool.functional.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static junit.framework.Assert.assertEquals;

public class MyProfilePage extends Page {

    public static final String MY_PROFILE_NAME_ID = "myProfileName";
    public static final String MY_PROFILE_PHONE_NUMBER_ID = "myProfilePhoneNumber";
    public static final String MY_PROFILE_EMAIL_ID = "myProfileEmail";

    @FindBy(how = How.ID, using = MY_PROFILE_NAME_ID)
    private WebElement myProfileName;

    @FindBy(how = How.ID, using = MY_PROFILE_PHONE_NUMBER_ID)
    private WebElement myProfilePhoneNumber;

    @FindBy(how = How.ID, using = MY_PROFILE_EMAIL_ID)
    private WebElement myProfileEmail;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(MY_PROFILE_NAME_ID));
    }

    public void verifyProfileDetails(String expectedUserName, String expectedUserEmail, String expectedUserPhoneNumber) {
        assertEquals(expectedUserName, myProfileName.getText());
        assertEquals(expectedUserPhoneNumber, myProfilePhoneNumber.getText());
        assertEquals(expectedUserEmail, myProfileEmail.getText());
    }
}
