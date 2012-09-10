package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.assertEquals;

public class JoinCarpoolPage extends Page {
    private static final String PICKUP_TIME_ID = "pickupTimeTextBox";
    private static final String ADDRESS_TEXT_BOX_ID = "addressTextBox";
    private static final String PICKUP_POINT_TEXT_BOX_ID = "pickupPointTextBox";
    private static final String PICKUP_TIME_TEXT_BOX_ID = "pickupTimeTextBox";
    private static final String SUBMIT_FORM_BUTTON_ID = "submitFormButton";
    private static final String JOIN_REQUEST_WARNING = "warning";

    @FindBy(how = How.ID, using = ADDRESS_TEXT_BOX_ID)
    private WebElement addressTextBox;

    @FindBy(how = How.ID, using = PICKUP_POINT_TEXT_BOX_ID)
    private WebElement pickupPointTextBox;

    @FindBy(how = How.ID, using = PICKUP_TIME_TEXT_BOX_ID)
    private WebElement pickupTimeTextBox;

    @FindBy(how = How.ID, using = SUBMIT_FORM_BUTTON_ID)
    private WebElement submitFormButton;

    public JoinCarpoolPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        try {
            webDriver.findElement(By.id(PICKUP_TIME_TEXT_BOX_ID));
            waitForElementToLoad(By.id(PICKUP_TIME_TEXT_BOX_ID));
        } catch (NoSuchElementException nse) {
            waitForElementToLoad(By.className(JOIN_REQUEST_WARNING));
        }
    }

    public JoinCarpoolPage enterJoinRequestDetails() {
        addressTextBox.sendKeys("Gate 2, Diamond District");
        pickupPointTextBox.sendKeys("Gate 2");
        pickupTimeTextBox.clear();
        pickupTimeTextBox.sendKeys("07:45");
        return this;
    }

    public ViewCarpoolPage submitForm() {
        submitFormButton.click();
        return new ViewCarpoolPage(webDriver);
    }
}
