package smartpool.functional;


import org.junit.Test;
import smartpool.functional.page.HomePage;
import smartpool.functional.page.LoginPage;
import smartpool.functional.page.ViewCarpoolPage;


public class ViewProfileTest extends BaseTest {

    @Test
    public void viewMyProfileAndItsDetails() {
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.goToMyProfilePage().verifyMyProfileDetails();
    }
}
