package smartpool.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class EnvironmentLoader {
    public String currentEnvironment;
    public static final String HOST_NAME = "hostName";
    public static final String PORT_NUMBER = "port";
    public static final String APPLICATION_NAME = "applicationName";
    public static final String APPLICATION_PATH = "applicationPath";
    public static final String CAS_SERVER_URL = "casServerUrl";
    private Properties environmentConfiguration = new Properties();

    public static final String[] APPLICATION_URL = new String[]{
        EnvironmentLoader.HOST_NAME, EnvironmentLoader.PORT_NUMBER, EnvironmentLoader.APPLICATION_NAME
    };

    @Autowired
    public EnvironmentLoader(Properties appProperties) {
        this(System.getenv("SMARTPOOL_ENV") == null ? "dev" : System.getenv("SMARTPOOL_ENV"), appProperties);
    }

    public EnvironmentLoader(String currentEnvironment, Properties environmentConfiguration) {
        this.environmentConfiguration = environmentConfiguration;
        this.currentEnvironment = currentEnvironment;
    }

    public String getProperty(String property) {
        return getProperty(currentEnvironment, property);
    }

    public String getProperty(String environment, String property) {
        String propertyName = String.format("%s.%s", environment, property);
        return environmentConfiguration.getProperty(propertyName);
    }

    public String getPropertyList(String propertyPattern, String... settings) {
        List<String> substitutions = new ArrayList<String>();
        for (String property : settings) {
            substitutions.add(getProperty(currentEnvironment, property));
        }

        String pattern = environmentConfiguration.getProperty(propertyPattern);
        return String.format(pattern, substitutions.toArray());
    }
}
