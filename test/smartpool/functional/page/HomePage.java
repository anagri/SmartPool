package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static junit.framework.Assert.assertEquals;

public class HomePage extends Page {

    public static final String WELCOME_MESSAGE_ID = "welcomeMessage";
    @FindBy(how = How.ID, using = WELCOME_MESSAGE_ID)
    private WebElement welcomeMessage;

    @FindBy(how = How.ID, using = "listCarpools")
    private WebElement listCarpoolsLink;

    @FindBy(how = How.ID, using = "myProfile")
    private WebElement myProfileLink;

    @FindBy(how = How.ID, using = "searchBox")
    private WebElement searchCarpoolBox;

    @FindBy(how = How.ID, using = "createCarpool")
    private WebElement createCarpoolLink;

    @FindBy(how = How.ID, using = "logoutForm")
    private WebElement logoutForm;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(WELCOME_MESSAGE_ID));
    }

    public ListCarpoolsPage goToListCarpoolsPage() {
        listCarpoolsLink.click();
        return new ListCarpoolsPage(webDriver);
    }

    public MyProfilePage goToMyProfilePage() {
        myProfileLink.click();
        return new MyProfilePage(webDriver);
    }

    public CreateCarpoolPage goToCreateCarpoolPage() {
        createCarpoolLink.click();
        return new CreateCarpoolPage(webDriver);
    }


    public void verifyWelcomeMessagePresent() {
        assertEquals("Welcome to SmartPool!", welcomeMessage.getText());
    }

    public SearchResultPage enterSearchQuery(String searchQuery) {
        searchCarpoolBox.click();
        searchCarpoolBox.sendKeys(searchQuery);
        searchCarpoolBox.submit();
        return new SearchResultPage(webDriver, searchQuery);
    }

    public LogoutPage logout() {
        logoutForm.submit();
        return new LogoutPage(webDriver);
    }
}
