package smartpool.functional;

import org.junit.Test;
import smartpool.functional.page.LoginPage;


public class LoginTest extends BaseTest {

    @Test
    public void loginWithWrongCredentialsAndVerifyLoginFailure() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.invalidLogin().verifyLoginFailureMessage();
    }
}
