package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    ExtentTest test;
    ExtentReports extent = Report.getExtentReport();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
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
        IScreenshot.takeScreenshot(driver, testMethodName);
        String scrShotLocation = IPropertyReader.getRelativePathForScreenshot() + testMethodName + ".png";

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
        // TODO Auto-generated method stub
        extent.flush();
    }

}
