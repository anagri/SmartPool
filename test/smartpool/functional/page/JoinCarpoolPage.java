package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JoinCarpoolPage extends Page {
    private static final String PICKUP_TIME_ID = "pickupTimeTextBox";

    public JoinCarpoolPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(PICKUP_TIME_ID));
    }

    public void enterJoinRequestDetails() {

    }
}
