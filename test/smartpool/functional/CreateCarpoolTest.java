package smartpool.functional;

import org.junit.Test;
import smartpool.functional.page.HomePage;
import smartpool.functional.page.LoginPage;

public class CreateCarpoolTest extends BaseTest {
    @Test
    public void shouldCreateANewCarpool() throws Exception {
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.goToCreateCarpoolPage()
                .enterCarpoolDetails()
                .verifyDetailsOfNewCarpool();
    }
}
