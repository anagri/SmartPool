package smartpool.functional;

import org.junit.After;
import org.junit.Test;
import smartpool.functional.page.HomePage;
import smartpool.functional.page.LoginPage;

public class JoinCarpoolTest extends BaseTest {
    @Test
    public void sendJoinRequestWithoutErrors() throws Exception {
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.goToListCarpoolsPage()
                .goToVacantCarpoolDetails()
                .goToJoinCarpoolPage()
                .enterJoinRequestDetails()
                .submitForm()
                .goToJoinCarpoolPage()
                .verifyFormSubmitted();
    }

    @Override
    @After
    public void tearDown() {
        super.tearDown();

    }
}
