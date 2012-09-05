package smartpool.functional.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static junit.framework.Assert.assertEquals;

public class MyProfilePage extends Page {

    public static final String MY_PROFILE_NAME_ID = "myProfileName";
    @FindBy(how = How.ID, using = MY_PROFILE_NAME_ID)
    private WebElement myProfileName;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(MY_PROFILE_NAME_ID));
    }

    public void verifyProfileDetails(String expectedUserName) {
        assertEquals(expectedUserName, myProfileName.getText());
    }
}
