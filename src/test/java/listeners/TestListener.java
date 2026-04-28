package listeners;

import base.BaseTest;
import com.aventstack.extentreports.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ScreenshotUtil;

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

        Object testClass = result.getInstance();

        WebDriver driver = ((BaseTest) testClass).getDriver();

        String path = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());

        test.get().fail(result.getThrowable())
                .addScreenCaptureFromPath(path);
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}