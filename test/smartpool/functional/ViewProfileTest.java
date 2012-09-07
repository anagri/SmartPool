package smartpool.functional;


import org.junit.Test;
import smartpool.functional.page.HomePage;
import smartpool.functional.page.LoginPage;


public class ViewProfileTest extends BaseTest {

    @Test
    public void viewMyProfileAndItsDetails() {
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.goToMyProfilePage().
                verifyProfileDetails("Test User", "test@thoughtworks.com");
    }

    @Test
    public void viewBuddyProfileAndItsDetails() {
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.goToListCarpoolsPage().
                gotoViewCarpoolDetails().
                goToBuddyProfilePage().
                verifyProfileDetails("Ayush Tulsyan", "ayusht@thoughtworks.com");
    }
}

