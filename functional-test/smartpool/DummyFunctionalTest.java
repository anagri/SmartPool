package smartpool;

import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.Assert.assertTrue;

public class DummyFunctionalTest {

    private Selenium selenium;

    @Before
    public void setUp() throws Exception {
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "https://www.google.com/";
        selenium = new WebDriverBackedSelenium(driver, baseUrl);
    }

    @Test
    public void testGoogle() throws Exception {
        selenium.open("/");
        assertTrue(selenium.isTextPresent("Google"));
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}