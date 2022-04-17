package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface IPropertyReader {

    static String getPropertyFromFile(String propertyName) {

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

     static String getUrl() {
        return getProperty("url");
    }

     static Browser getBrowser() {
        return Browser.valueOf(getProperty("browser"));
    }

     static String getAbsolutePathForScreenshot() {
        return (getProperty("absolutePathForScreenshot"));
    }

     static String getPathForReport() {
        return getProperty("pathForReport");
    }

     static String getRelativePathForScreenshot() {
        return getProperty("relativePathForScreenshot");
    }

     static String getPathForLanguageTestExcelFile() {
        return getProperty("pathForLanguageTestExcelFile");
    }


}
