package smartpool.functional.page;

import org.hamcrest.BaseMatcher;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import smartpool.util.matchers.ContainsMatcher;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static smartpool.util.matchers.ContainsMatcher.contains;

public class ViewCarpoolPage extends Page {

    public static final String CARPOOL_NAME_ID = "carpoolName";
    public static final String MORE_DETAILS_BUTTON = "moreDetailsButton";
    private static final String JOIN_REQUEST_BUTTON = "joinRequestButton";
    private static final String STATUS_MESSAGE = "status-message";

    @FindBy(how = How.ID, using = CARPOOL_NAME_ID)
    private WebElement carpoolName;

    @FindBy(how = How.LINK_TEXT, using = "Ayush Tulsyan")
    private WebElement ayushtLink;

    @FindBy(how = How.ID, using = "routePoints")
    private WebElement routepoints;

    @FindBy(how = How.CLASS_NAME, using = MORE_DETAILS_BUTTON)
    private WebElement moreDetailsButton;

    @FindBy(how = How.LINK_TEXT, using = "More Details")
    private WebElement moreDetailsButtonLink;

    @FindBy(how = How.ID, using = JOIN_REQUEST_BUTTON)
    private WebElement joinRequestButton;

    @FindBy(how = How.CLASS_NAME, using = STATUS_MESSAGE)
    private WebElement statusMessage;

    public ViewCarpoolPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.className(MORE_DETAILS_BUTTON));
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
        joinRequestButton.submit();
        return new JoinCarpoolPage(webDriver);
    }

    public void goToAndVerifyViewMoreDetails() {
        moreDetailsButtonLink.click();
        waitForElementToVisible(By.id("routePoints"));
        assertThat(routepoints.getText(), contains("Dell Office"));
    }

    public void verifyJoinRequestPending() {
        Assert.assertEquals("JOIN REQUEST PENDING", statusMessage.getText());
    }
}
