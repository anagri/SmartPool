import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

public class CASTest extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://castest.thoughtworks.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testWrongUsernameAndPassword() throws Exception {
		selenium.open("/cas/login?service=http%3A%2F%2Flocalhost%3A9090%2FSmartPool%2F");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("id=username")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.type("id=username", "wrongusername");
		selenium.type("id=password", "wrongpassword");
		selenium.click("name=submit");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent("The credentials you provided cannot be determined to be authentic"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
