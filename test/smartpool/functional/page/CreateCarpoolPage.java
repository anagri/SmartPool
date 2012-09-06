package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateCarpoolPage extends Page {

    public static final String MAIN_CONTAINER_CLASS_NAME = "mainContainer";

    @FindBy(how = How.NAME, using = "name")
    private WebElement nameTextBox;

    @FindBy(how = How.NAME, using = "startDateForm")
    private WebElement startDateTextBox;

    @FindBy(how = How.NAME, using = "pickupPoint")
    private WebElement pickupPointTextBox;

    @FindBy(how = How.NAME, using = "pickupTime")
    private WebElement pickupTimeTextBox;

    @FindBy(how = How.NAME, using = "officeETAForm")
    private WebElement officeETATextBox;

    @FindBy(how = How.NAME, using = "officeETDForm")
    private WebElement officeETDTextBox;

    @FindBy(how = How.NAME, using = "routePointForm")
    private WebElement routePointTextBox;

    @FindBy(how = How.NAME, using = "submit")
    private WebElement submitButton;

    public CreateCarpoolPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.className(MAIN_CONTAINER_CLASS_NAME));
    }

    public ViewCarpoolPage enterCarpoolDetails() {
        nameTextBox.sendKeys("Carpool-FNTEST");
        startDateTextBox.sendKeys("15/10/2012");
        pickupPointTextBox.sendKeys("Domlur");
        pickupTimeTextBox.sendKeys("10:15");
        officeETATextBox.sendKeys("11:00");
        officeETDTextBox.sendKeys("18:00");
        routePointTextBox.sendKeys("Domlur, Oasis Mall");
        submitButton.click();
        return new ViewCarpoolPage(webDriver);
    }
}