package smartpool.functional;

import org.junit.Ignore;
import org.junit.Test;
import smartpool.functional.page.HomePage;
import smartpool.functional.page.LoginPage;

public class CreateProfileTest extends BaseTest {

    @Test
    @Ignore
    public void shouldCreateANewProfile() throws Exception {
        String testUser = "Test User";
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.myProfileRedirectToCreateProfilePage()
                .enterProfileDetails()
                .verifyDetailsOfNewBuddy(testUser);
    }
}
