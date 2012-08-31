package smartpool;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

public class cas2 extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://castest.thoughtworks.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testCas2() throws Exception {
		selenium.open("http://localhost:9090/smartpool");
		selenium.type("id=username", "wrongid");
		selenium.type("id=password", "lollollol");
		selenium.click("name=submit");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent("The credentials you provided cannot be determined to be authentic."));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}