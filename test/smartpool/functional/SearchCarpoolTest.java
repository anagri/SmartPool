package smartpool.functional;

import org.junit.Test;
import smartpool.functional.page.HomePage;
import smartpool.functional.page.LoginPage;

public class SearchCarpoolTest extends BaseTest{

    @Test
    public void searchCarpoolAndVerifyResultCount() {
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.enterSearchQuery()
                .verifyResultCount();
    }
}
