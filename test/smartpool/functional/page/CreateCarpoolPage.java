package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateCarpoolPage extends Page {

    public static final String MAIN_CONTAINER_CLASS_NAME = "mainContainer";

    @FindBy(how = How.NAME, using = "to")
    private WebElement toTextBox;

    @FindBy(how = How.NAME, using = "from")
    private WebElement fromTextBox;

    @FindBy(how = How.NAME, using = "proposedStartDate")
    private WebElement startDateTextBox;

    @FindBy(how = How.NAME, using = "pickupPoint")
    private WebElement pickupPointTextBox;

    @FindBy(how = How.NAME, using = "pickupTime")
    private WebElement pickupTimeTextBox;

    @FindBy(how = How.NAME, using = "officeArrivalTime")
    private WebElement officeETATextBox;

    @FindBy(how = How.NAME, using = "officeDepartureTime")
    private WebElement officeETDTextBox;

    @FindBy(how = How.NAME, using = "routePoints")
    private WebElement routePointTextBox;

    @FindBy(how = How.NAME, using = "submit")
    private WebElement submitButton;

    @FindBy(how = How.NAME, using = "capacity")
    private WebElement capacityTextBox;

    public CreateCarpoolPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.className(MAIN_CONTAINER_CLASS_NAME));
    }

    public ViewCarpoolPage enterCarpoolDetails(String from) {
        fromTextBox.sendKeys(from);
        toTextBox.sendKeys("TW");
        startDateTextBox.sendKeys("15/10/2012");
        pickupPointTextBox.sendKeys("Domlur");
        pickupTimeTextBox.sendKeys("10:15");
        capacityTextBox.sendKeys("4");
        officeETATextBox.sendKeys("11:00");
        officeETDTextBox.sendKeys("18:00");
        routePointTextBox.sendKeys("Domlur, Oasis Mall");
        submitButton.click();
        return new ViewCarpoolPage(webDriver);
    }
}


