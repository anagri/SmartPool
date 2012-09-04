package smartpool.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EnvironmentLoader {
    public String currentEnvironment;
    public static final String HOST_NAME = "hostName";
    public static final String PORT_NUMBER = "port";
    public static final String APPLICATION_NAME = "applicationName";
    public static final String APPLICATION_PATH = "applicationPath";
    private Properties environmentConfiguration;
    public static final String[] APPLICATION_URL = new String[]{
            EnvironmentLoader.HOST_NAME, EnvironmentLoader.PORT_NUMBER, EnvironmentLoader.APPLICATION_NAME
    };


    public EnvironmentLoader(String currentEnvironment, Properties environmentConfiguration) {
        this.environmentConfiguration = environmentConfiguration;
        this.currentEnvironment = currentEnvironment;

    }

    public EnvironmentLoader() {
        this.currentEnvironment = System.getenv("SMARTPOOL_ENV");
        environmentConfiguration = new Properties();
        try {
            environmentConfiguration.load(new FileInputStream("build.properties"));
        } catch (IOException e) {
            System.err.println(String.format("Property file read fail. %s", e.getMessage()));
        }
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
