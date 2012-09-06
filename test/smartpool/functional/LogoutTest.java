package smartpool.functional;

import org.junit.Test;
import smartpool.functional.page.HomePage;
import smartpool.functional.page.LoginPage;

import static org.junit.Assert.assertTrue;

public class LogoutTest extends BaseTest{

    @Test
    public void logoutAndPrintSuccessMessage() throws Exception {
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.logout()
                .verifySuccessMessage();
    }

    @Test
    public void logoutAndNeedForLogin() throws Exception{
        HomePage homePage = new LoginPage(webDriver).login();
        assertTrue(homePage.logout().askLoginWhenGoToHomePage());
    }
}
