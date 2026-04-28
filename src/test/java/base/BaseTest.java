package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

//before ThreDLocal execution
//public class BaseTest {
//
//    public WebDriver driver;
//
//    @BeforeMethod(alwaysRun = true)
//    public void setup() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//
//        driver.get("https://dev-in-frontend.vercel.app/");
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }
//}


//after thread local execution for report
public class BaseTest {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());

        getDriver().manage().window().maximize();
        getDriver().get("https://dev-in-frontend.vercel.app/");
    }

//    @AfterMethod(alwaysRun = true)
//    public void tearDown() {
//        getDriver().quit();
//        driver.remove();
//    }
}
