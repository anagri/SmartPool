package smartpool.functional;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import org.junit.Ignore;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import smartpool.util.EnvironmentLoader;
//import smartpool.domain.Carpool;

import java.sql.*;
import java.util.regex.Pattern;

@Ignore
public class VC1 extends SeleneseTestCase {
    Connection connection;
	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = new EnvironmentLoader().getPropertyList(EnvironmentLoader.APPLICATION_PATH,
                EnvironmentLoader.APPLICATION_URL);
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
        //Class.forName("com.mysql.jdbc.Driver");
        //Connection con = DriverManager.getConnection("jdbc:mysql://localhost/smartpool", "root", "");
	}


	@Test
	public void testVC1() throws Exception {
		selenium.open("/carpool/carpool-1");
        //copied from CAS1 to ensure Login
        selenium.type("id=username", "test.twu");
        selenium.type("id=password", "Th0ughtW0rks@12");
        selenium.click("name=submit");
        selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent("Carpool"));
		verifyTrue(selenium.isTextPresent("Buddy List"));
		verifyTrue(selenium.isTextPresent("Pickup Sequence 	Buddy Name 	Pickup Point 	Pickup Time"));
		verifyTrue(selenium.isTextPresent("Cab Type:"));
		verifyTrue(selenium.isTextPresent("Office ETA:")); //10:50:00

		verifyTrue(selenium.isTextPresent("Office ETD:")); //18:30:00
		verifyTrue(selenium.isTextPresent("Status:"));
		selenium.click("id=moreDetailsButton");
		verifyTrue(selenium.isTextPresent("Date Started:"));
		verifyTrue(selenium.isTextPresent("Capacity:"));//4


		verifyTrue(selenium.isTextPresent("Total Cab Charges:"));//100
		verifyTrue(selenium.isTextPresent("Route Plan"));

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}