package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {

//    @Test(groups = {"login"})
//    public void testLogin() {
//        LoginPage login = new LoginPage(driver);
//        //login.login("test@gmail.com", "Test@123456");
//        login.login("test1new@gmail.com", "Test@123456");
//    }

    //after applying page factory using assertions
    //@Test(groups = {"login"})
    @Test(groups = {"login"}, retryAnalyzer = utils.RetryAnalyzer.class)
    public void testLogin() {
        //before threadlocal
        //LoginPage login = new LoginPage(driver);
        //after threadlocal
        LoginPage login = new LoginPage(getDriver());
        login.login("test1new@gmail.com", "Test@123456");

        // Assertion example
//        String currentUrl = getDriver().getCurrentUrl();
//        System.out.println("Current URL: " + currentUrl);
//        assert currentUrl.contains("home") : "Login failed!";
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        boolean isLoggedIn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("img[alt='photo url']") // adjust if needed
                )
        ).isDisplayed();

        Assert.assertTrue(isLoggedIn,"Login failed");
    }
}