package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertTrue;


public class SearchResultPage extends Page {

    private static final String RESULTS_MESSAGE_ID = "resultsMessage";

    @FindBy(how = How.ID, using = RESULTS_MESSAGE_ID)
    private WebElement resultMessage;

    @FindBy(how = How.ID, using = "routePointList")
    private WebElement routePointList;

    public SearchResultPage(WebDriver webDriver, String searchQuery) {
        super(webDriver, searchQuery);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(RESULTS_MESSAGE_ID), searchQuery);
    }

    public SearchResultPage selectLocationFromRoutePointList(String routePoint) {
        new Select(routePointList).selectByValue(routePoint);
        return new SearchResultPage(webDriver, routePoint);
    }

    public void verifyResultCount(String resultCount) {
        assertTrue(resultMessage.getText().contains(resultCount));
    }
}
