package smartpool.functional;

import org.junit.Test;
import smartpool.functional.page.LoginPage;

public class HomePageTest extends BaseTest {

	@Test
	public void loginAndVerifyWelcomeMessageOnHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login().verifyWelcomeMessagePresent();
    }
}
