package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static junit.framework.Assert.assertEquals;

public class ViewCarpoolPage extends Page {

    public static final String CARPOOL_NAME_ID = "carpoolName";
    public static final String BUDDY_PROFILE_ID = "arnavku";
    public static final String MORE_DETAILS_BUTTON = "moreDetailsButton";
    private static final String JOIN_REQUEST_BUTTON = "joinRequestButton";

    @FindBy(how = How.ID, using = CARPOOL_NAME_ID)
    private WebElement carpoolName;

    @FindBy(how = How.LINK_TEXT, using = "Ayush Tulsyan")
    private WebElement ayushtLink;

    @FindBy(how = How.CLASS_NAME, using = MORE_DETAILS_BUTTON)
    private WebElement moreDetailsButton;

    @FindBy(how = How.ID, using = JOIN_REQUEST_BUTTON)
    private WebElement joinRequestButton;


    public ViewCarpoolPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(CARPOOL_NAME_ID));
    }

    public void verifyCarpoolDetails() {
        assertEquals("Carpool carpool-1", carpoolName.getText());
    }

    public ProfilePage goToBuddyProfilePage() {
        ayushtLink.click();
        return new ProfilePage(webDriver);
    }


    public void verifyDetailsOfNewCarpool() {
        assertEquals("Carpool from - to",carpoolName.getText());
    }

    public JoinCarpoolPage goToJoinCarpoolPage() {
        joinRequestButton.click();
        return new JoinCarpoolPage(webDriver);
    }

}