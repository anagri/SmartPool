package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class JoinCarpoolPage extends Page {
    private static final String PICKUP_TIME_ID = "pickupTimeTextBox";
    private static final String ADDRESS_TEXT_BOX_ID = "addressTextBox";
    private static final String PICKUP_POINT_TEXT_BOX_ID = "pickupPointTextBox";
    private static final String PICKUP_TIME_TEXT_BOX_ID = "pickupTimeTextBox";
    private static final String SUMBIT_FORM_BUTTON_ID = "submitFormButton";


    @FindBy(how = How.ID, using = ADDRESS_TEXT_BOX_ID)
    private WebElement addressTextBox;

    @FindBy(how = How.ID, using = PICKUP_POINT_TEXT_BOX_ID)
    private WebElement pickupPointTextBox;

    @FindBy(how = How.ID, using = PICKUP_TIME_TEXT_BOX_ID)
    private WebElement pickupTimeTextBox;

    @FindBy(how = How.ID, using = SUMBIT_FORM_BUTTON_ID)
    private WebElement submitFormButton;

    public JoinCarpoolPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(PICKUP_TIME_ID));
    }

    public JoinCarpoolPage enterJoinRequestDetails() {
        addressTextBox.sendKeys("Gate 2, Diamond District");
        pickupPointTextBox.sendKeys("Gate 2");
        pickupTimeTextBox.sendKeys("07:45");
        return this;
    }

    public void submitForm() {
        submitFormButton.click();
    }
}
