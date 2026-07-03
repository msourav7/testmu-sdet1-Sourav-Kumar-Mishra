package listeners;

import ai.AIExecutionSummaryAgent;
import ai.ExecutionSummaryAgent;
import ai.FailureAnalysisAgent;
import base.BaseTest;
import com.aventstack.extentreports.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("******** AI LISTENER EXECUTED ********");

        // Get current test class
        Object testClass = result.getInstance();

        WebDriver driver = null;

        if (testClass instanceof BaseTest) {
            driver = ((BaseTest) testClass).getDriver();
        }

        // Capture screenshot only if driver exists
        String screenshotPath = null;

        if (driver != null) {

            screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                            driver,
                            result.getMethod().getMethodName()
                    );
        }

        // AI Failure Analysis
        FailureAnalysisAgent agent =
                new FailureAnalysisAgent();

        String analysis =
                agent.analyze(
                        result.getMethod().getMethodName(),
                        result.getThrowable()
                );

        // Save AI Report
        String reportPath =
                AIReportUtil.saveAnalysis(
                        analysis,
                        result.getMethod().getMethodName()
                );

        // Save plain text report
        try {

            Path folder =
                    Path.of("reports", "ai-analysis");

            Files.createDirectories(folder);

            Files.writeString(

                    folder.resolve(
                            result.getMethod().getMethodName() + ".txt"
                    ),

                    analysis

            );

        } catch (IOException e) {

            e.printStackTrace();

        }

        // Extent Report
        test.get().fail(result.getThrowable());

        // Attach screenshot only if available
        if (screenshotPath != null) {

            test.get().addScreenCaptureFromPath(screenshotPath);

        }

        test.get().info(
                "<b>🤖 AI Failure Analysis</b><br><pre>"
                        + analysis
                        + "</pre>"
        );

        test.get().info(
                "AI Report saved at : " + reportPath
        );
    }
//    @Override
//    public void onTestFailure(ITestResult result) {
//
//        Object testClass = result.getInstance();
//
//        WebDriver driver = ((BaseTest) testClass).getDriver();
//
//        String path = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
//
//        test.get().fail(result.getThrowable())
//                .addScreenCaptureFromPath(path);
//    }

    @Override
    public void onFinish(ITestContext context) {

        // Flush Extent Report
        extent.flush();

        // Generate AI Execution Summary
        ExecutionSummaryAgent agent =
                new ExecutionSummaryAgent();

        String summary =
                agent.generateSummary();

        // Save Summary
        AIReportUtil.saveAnalysis(
                summary,
                "ExecutionSummary"
        );

        // Print Summary in Console
        System.out.println(summary);

        // Clear execution logs for next run
        ExecutionCollector.clear();
    }
}