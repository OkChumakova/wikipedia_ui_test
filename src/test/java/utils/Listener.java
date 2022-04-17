package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class Listener implements ITestListener {
    ExtentReports extent = Report.getExtentReport();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();

        String paramName = result.getParameters().length > 0 ?
                " " + Arrays.asList(result.getParameters())
                : "";
        ExtentTest test = extent.createTest(methodName + paramName);
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
        String testMethodName = result.getMethod().getMethodName();

        // Take screenshot
        Screenshot.takeScreenshot(driver, testMethodName);
        String scrShotLocation = PropertyReader.getRelativePathForScreenshot() + testMethodName + ".png";

        extentTest.get().addScreenCaptureFromPath(scrShotLocation, testMethodName);
    }

    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
