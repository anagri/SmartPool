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

    @FindBy(how = How.ID, using = CARPOOL_NAME_ID)
    private WebElement carpoolName;

    @FindBy(how = How.ID, using = "ayusht")
    private WebElement ayushtLink;

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

    public MyProfilePage goToBuddyProfilePage() {
        ayushtLink.click();
        return new MyProfilePage(webDriver);
    }
}
