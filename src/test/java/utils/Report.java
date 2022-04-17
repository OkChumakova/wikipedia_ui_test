package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public abstract class Report {

    private static ExtentReports extentReport;

    public static void createReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(PropertyReader.getPathForReport());

        reporter.config().setReportName("Wikipedia Automation Results");
        reporter.config().setDocumentTitle("UI Results");

        extentReport = new ExtentReports();
        extentReport.attachReporter(reporter);
        extentReport.setSystemInfo("Tester", "Oksana Chumakova");
    }

    public static ExtentReports getExtentReport() {
        if (extentReport == null){
            createReport();
        }
        return extentReport;
    }
}

