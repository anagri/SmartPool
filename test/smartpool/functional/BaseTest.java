package smartpool.functional;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;
import smartpool.functional.page.Page;

public abstract class BaseTest {

    protected FirefoxDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.get(Page.BASE_URL);
    }

    @After
    public void tearDown() {
        webDriver.manage().deleteAllCookies();
        webDriver.quit();
    }
}
