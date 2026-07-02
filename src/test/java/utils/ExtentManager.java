//Handling report creation
package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

           // reporter.config().setReportName("DevIn Automation Report");
            reporter.config().setReportName("TestMu AI SDET Assignment Report");
            //reporter.config().setDocumentTitle("Test Results");
            reporter.config().setDocumentTitle("AI Powered Test Automation Framework Assignment ");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            // ===== System Information =====
            extent.setSystemInfo("Framework", "Selenium + Java");
            extent.setSystemInfo("Automation Framework", "TestNG + POM");
            extent.setSystemInfo("AI Agents", "Test Generation Agent, Failure Analysis Agent");
            extent.setSystemInfo("LLM", "Google Gemini 2.5 Flash");
            extent.setSystemInfo("Reporting", "Extent Reports");
            extent.setSystemInfo("Author", "Sourav Kumar Mishra");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Execution", "Local");
            extent.setSystemInfo("Build Version", "1.0");
        }
        return extent;
    }
}