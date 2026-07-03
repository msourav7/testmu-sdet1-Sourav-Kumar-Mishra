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

        try {

            System.out.println("******** AI LISTENER EXECUTED ********");

            Object testClass = result.getInstance();

            WebDriver driver = null;

            if (testClass instanceof BaseTest) {
                driver = ((BaseTest) testClass).getDriver();
            }

            String screenshotPath = null;

            if (driver != null) {
                screenshotPath = ScreenshotUtil.captureScreenshot(
                        driver,
                        result.getMethod().getMethodName()
                );
            }

            FailureAnalysisAgent agent = new FailureAnalysisAgent();

            String analysis = agent.analyze(
                    result.getMethod().getMethodName(),
                    result.getThrowable()
            );

            String reportPath = AIReportUtil.saveAnalysis(
                    analysis,
                    result.getMethod().getMethodName()
            );

            try {

                Path folder = Path.of("reports", "ai-analysis");

                Files.createDirectories(folder);

                Files.writeString(
                        folder.resolve(result.getMethod().getMethodName() + ".txt"),
                        analysis
                );

            } catch (IOException e) {
                e.printStackTrace();
            }

            ExtentTest extentTest = test.get();

            if (extentTest != null) {

                extentTest.fail(result.getThrowable());

                if (screenshotPath != null) {
                    extentTest.addScreenCaptureFromPath(screenshotPath);
                }

                extentTest.info(
                        "<b>🤖 AI Failure Analysis</b><br><pre>"
                                + analysis
                                + "</pre>"
                );

                extentTest.info(
                        "AI Report saved at : " + reportPath
                );
            }

        } catch (Exception e) {

            System.out.println("Listener Error: " + e.getMessage());
            e.printStackTrace();

        }
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