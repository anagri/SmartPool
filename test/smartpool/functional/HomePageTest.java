package smartpool.functional;

import org.junit.Test;
import smartpool.functional.page.HomePage;
import smartpool.functional.page.LoginPage;

import static org.junit.Assert.assertThat;
import static smartpool.util.matchers.ContainsMatcher.contains;

public class HomePageTest extends BaseTest {

    @Test
    public void loginAndVerifyWelcomeMessageOnHomePage() {
        HomePage homePage = new LoginPage(webDriver).login();
        assertThat(homePage.welcomeMessage.getText(), contains("SmartPool"));
        assertThat(homePage.logoutForm.getText(), contains("Test User"));
    }
}
