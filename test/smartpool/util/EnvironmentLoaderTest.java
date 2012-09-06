package smartpool.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static junit.framework.Assert.assertEquals;

public class EnvironmentLoaderTest {


    private Properties expected_properties;
    private EnvironmentLoader loader1;
    private EnvironmentLoader loader2;

    private String expected_environment1_hostName = "localhost";
    private String expected_environment1_port = "9090";
    private String expected_environment1_applicationName = "anothername";
    private String expected_environment1_casServerUrl = "testCAS";

    private String expected_environment2_hostName = "10.10.10.10";
    private String expected_environment2_port = "7070";
    private String expected_environment2_applicationName = "somename";

    @Before
    public void setUp() throws Exception {
        expected_properties = new Properties();
        expected_properties.setProperty("environment1.hostName", expected_environment1_hostName);
        expected_properties.setProperty("environment1.port", expected_environment1_port);
        expected_properties.setProperty("environment1.applicationName", expected_environment1_applicationName);
        expected_properties.setProperty("environment1.casServerUrl", expected_environment1_casServerUrl);

        expected_properties.setProperty("environment2.hostName", expected_environment2_hostName);
        expected_properties.setProperty("environment2.applicationName", expected_environment2_applicationName);
        expected_properties.setProperty("environment2.port", expected_environment2_port);

        expected_properties.setProperty(EnvironmentLoader.APPLICATION_PATH, "http://%s:%s/%s");

        loader1 = new EnvironmentLoader("environment1", expected_properties);
        loader2 = new EnvironmentLoader("environment2", expected_properties);
    }

    @Test
    public void environment1HasOwnApplicationName() throws Exception {
        assertEquals(expected_environment1_applicationName, loader1.getProperty(EnvironmentLoader.APPLICATION_NAME));
    }

    @Test
    public void environment2HasOwnApplicationName() throws Exception {
        assertEquals(expected_environment2_applicationName, loader2.getProperty(EnvironmentLoader.APPLICATION_NAME));
    }

    @Test
    public void shouldDeriveApplicationPathFromEnvironment() {
        assertEquals("http://localhost:9090/anothername", loader1.getPropertyList("applicationPath",
                EnvironmentLoader.HOST_NAME, EnvironmentLoader.PORT_NUMBER, EnvironmentLoader.APPLICATION_NAME));
    }

    @Test
    public void environment1HasOwnCasServerUrl() throws Exception {
        assertEquals(expected_environment1_casServerUrl, loader1.getProperty(EnvironmentLoader.CAS_SERVER_URL));
    }

    @Test
    public void shouldDeriveApplicationPathFromEnvironment2() {
        assertEquals("http://10.10.10.10:7070/somename", loader2.getPropertyList("applicationPath",
                EnvironmentLoader.HOST_NAME, EnvironmentLoader.PORT_NUMBER, EnvironmentLoader.APPLICATION_NAME));
    }

    @Test
    public void testShouldUseFixedPropertyListForEnvironment1() throws Exception {
        assertEquals("http://localhost:9090/anothername", loader1.getPropertyList("applicationPath",
                EnvironmentLoader.APPLICATION_URL));
    }

    @Test
    public void testShouldUseFixedPropertyList() throws Exception {
        assertEquals("http://10.10.10.10:7070/somename", loader2.getPropertyList("applicationPath",
                EnvironmentLoader.APPLICATION_URL));
    }
}
