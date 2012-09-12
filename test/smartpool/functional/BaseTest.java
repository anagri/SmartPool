package smartpool.functional;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;
import smartpool.functional.page.LoginPage;
import smartpool.functional.page.Page;

public abstract class BaseTest {

    protected FirefoxDriver webDriver;
    private static boolean TEST_USER_CREATED = false;

    @Before
    public void setUp() {
        createWebDriver();
        if (!TEST_USER_CREATED) {
            createProfileForTestUser();
        }
    }

    @After
    public void tearDown() {
        destroyWebDriver();
    }

    private void createWebDriver() {
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.get(Page.BASE_URL);
    }

    private void destroyWebDriver() {
        webDriver.manage().deleteAllCookies();
        webDriver.quit();
    }

    private void createProfileForTestUser() {
        new LoginPage(webDriver).login().myProfileRedirectToCreateProfilePage().enterProfileDetails().verifyDetailsOfNewBuddy("Test User");
        TEST_USER_CREATED = true;
        destroyWebDriver();
        createWebDriver();
    }
}
