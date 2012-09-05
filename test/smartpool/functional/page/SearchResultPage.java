package smartpool.functional.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.junit.Assert;


public class SearchResultPage extends Page{

    private static final String RESULT_MESSAGE_ID = "resultsFoundMessage";

    @FindBy(how = How.ID, using = RESULT_MESSAGE_ID)
    private WebElement resultMessage;

    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void waitForThePageToLoad() {
        waitForElementToLoad(By.id(RESULT_MESSAGE_ID));
    }

    public void verifyResultCount() {
        Assert.assertTrue(resultMessage.getText().contains("1 result"));
    }
}
