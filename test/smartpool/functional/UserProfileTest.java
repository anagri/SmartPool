package smartpool.functional;

import org.junit.Test;
import smartpool.functional.page.HomePage;
import smartpool.functional.page.LoginPage;

public class UserProfileTest extends  BaseTest {

    @Test
    public void shouldVerifyHeaderPresentForMyProfile() throws Exception {
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.goToMyProfilePage().verifyMyProfileDetails();
    }

    @Test
    public void shouldVerifyHeaderPresentForBuddyProfile() throws Exception {
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.goToListCarpoolsPage().gotoViewCarpoolDetails().goToUserProfile().verifyProfileDetails();

    }
}
