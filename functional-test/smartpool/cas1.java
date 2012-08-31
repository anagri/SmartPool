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

public class cas1 extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://castest.thoughtworks.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testCas1() throws Exception {
		selenium.open("http://10.10.15.121:9090/smartpool-qa/carpool/carpool-1");
		selenium.type("id=username", "test.twu");
		selenium.type("id=password", "Th0ughtW0rks@12");
		selenium.click("name=submit");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent("Anna's new homepage"));
		selenium.open("http://castest.thoughtworks.com/cas/logout");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}