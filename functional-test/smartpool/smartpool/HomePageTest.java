package smartpool;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import smartpool.util.EnvironmentLoader;

import java.util.regex.Pattern;

public class HomePageTest extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = new EnvironmentLoader().getProperty(EnvironmentLoader.APPLICATION_NAME);
        new EnvironmentLoader().getPropertyList(EnvironmentLoader.APPLICATION_PATH,
                EnvironmentLoader.HOST_NAME, EnvironmentLoader.PORT_NUMBER, EnvironmentLoader.APPLICATION_NAME);
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testFntest1() throws Exception {
		selenium.open("http://10.10.15.121:9090/smartpool-uat");
        String username = "test.twu";
        selenium.type("id=username", username);
		selenium.type("id=password", "Th0ughtW0rks@12");
		selenium.click("name=submit");
		selenium.waitForPageToLoad("30000");
		verifyEquals("SmartPool", selenium.getTitle());
		verifyTrue(selenium.isTextPresent("Welcome to SmartPool!"));
		verifyTrue(selenium.isTextPresent(username));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
