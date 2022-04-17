package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public interface IScreenshot {

     static void takeScreenshot(WebDriver driver, String methodName){
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(IPropertyReader.getAbsolutePathForScreenshot() + methodName + ".png");

            try {
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
