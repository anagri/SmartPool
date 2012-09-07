package smartpool.functional;

import org.junit.Test;
import smartpool.functional.page.HomePage;
import smartpool.functional.page.LoginPage;

public class ViewCarpoolTest extends BaseTest {

    @Test
    public void viewCarpoolAndItsDetails() {
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.goToListCarpoolsPage()
                .gotoViewCarpoolDetails()
                .verifyCarpoolDetails();
    }

    @Test
    public void  viewCarpoolAndMoreDetails() {
       HomePage homePage = new LoginPage(webDriver).login();
        homePage.goToListCarpoolsPage()
                .gotoViewCarpoolDetails()
                .goToAndVerifyViewMoreDetails();
    }

}