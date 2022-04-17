package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static String getPropertyFromFile(String propertyName) {

        Properties prop = new Properties();

        try (InputStream input = new FileInputStream("src/test/resources/framework.properties")) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }

    private static String getProperty(String propertyName) {
        if ((System.getProperty(propertyName) == null)) {
            return getPropertyFromFile(propertyName);
        } else {
            return System.getProperty(propertyName);
        }
    }

    public static String getUrl() {
        return getProperty("url");
    }

    public static Browser getBrowser() {
        return Browser.valueOf(getProperty("browser"));
    }

    public static String getAbsolutePathForScreenshot() {
        return (getProperty("absolutePathForScreenshot"));
    }

    public static String getPathForReport() {
        return getProperty("pathForReport");
    }

    public static String getRelativePathForScreenshot() {
        return getProperty("relativePathForScreenshot");
    }

    public static String getPathForLanguageTestExcelFile() {
        return getProperty("pathForLanguageTestExcelFile");
    }
}
