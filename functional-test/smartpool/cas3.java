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

public class cas3 extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://castest.thoughtworks.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testCas3() throws Exception {
		selenium.open("http://10.10.15.121:9090/smartpool-test/carpool");
		verifyEquals("CAS – Central Authentication Service", selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}