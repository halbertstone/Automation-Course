package course.base;

import webdriver.PropertiesResourceManager;

/**
 * This class is used for organizing work with credentials
 */
public class CredentialsHelper {
    public static PropertiesResourceManager props = new PropertiesResourceManager("credentials.properties");

    /**
     * This method is used for retrieving username
     * @return Username
     */
    public static String getUsername() {
        return props.getProperty("onliner_login", "autokurs");
    }

    /**
     * This method is used for retrieving password
     * @return Password
     */
    public static String getPassword() {
        return props.getProperty("onliner_password", "q1w2e3");
    }
}
