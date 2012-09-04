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
import smartpool.functional.page.HomePage;
import smartpool.functional.page.LoginPage;
import smartpool.util.EnvironmentLoader;
//import smartpool.domain.Carpool;

import java.sql.*;
import java.util.regex.Pattern;

public class ViewCarpoolTest extends BaseTest {

	@Test
	public void viewCarpoolAndItsDetails() {
        HomePage homePage = new LoginPage(webDriver).login();
        homePage.goToViewCarpoolPage().verifyCarpoolDetails();
	}
}