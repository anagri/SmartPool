package smartpool;


import com.thoughtworks.selenium.SeleneseTestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CASTest extends SeleneseTestBase {

    private String homeUrl;
    private String serverUrl;

    @Before
    public void setUp() throws Exception {
        WebDriver driver = new FirefoxDriver();
        serverUrl = "https://castest.thoughtworks.com/";
        homeUrl = "http://localhost:9090/SmartPool/";
        selenium = new WebDriverBackedSelenium(driver, serverUrl);
    }

    @Test
    public void shouldRedirectToCASTest() throws Exception {
        selenium.open(homeUrl);
        verifyEquals(serverUrl, selenium.getLocation());
    }

    @Test
    public void casWrongUsernameWrongPasswordTest() throws Exception {
        selenium.open(homeUrl);
        selenium.type("id=username", "wrongusername");
        selenium.type("id=password", "wrongpassword");
        selenium.click("name=submit");
        selenium.waitForPageToLoad("30000");
        assertTrue(selenium.isTextPresent("exact:The credentials you provided cannot be determined to be authentic.\n"));
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}

