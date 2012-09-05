package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ListCarpoolsPage extends Page {

    public static final String CARPOOL_1_ID = "carpool-1";
    @FindBy(how = How.ID, using = CARPOOL_1_ID)
    private WebElement carpool1;

    public ListCarpoolsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(CARPOOL_1_ID));
    }

    public ViewCarpoolPage gotoViewCarpoolDetails() {
        carpool1.click();
        return new ViewCarpoolPage(webDriver);
    }
}
