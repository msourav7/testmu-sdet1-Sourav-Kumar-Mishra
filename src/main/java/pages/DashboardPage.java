package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    public DashboardPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDashboardLoaded() {

        try {

            wait.until(ExpectedConditions.urlContains("dashboard"));

            return true;

        } catch (Exception e) {

            return false;

        }

    }

    public boolean isProfileVisible() {

        try {

            WebElement profile = wait.until(

                    ExpectedConditions.visibilityOfElementLocated(

                            By.cssSelector("img[alt='photo url']")
                    )
            );

            return profile.isDisplayed();

        } catch (Exception e) {

            return false;

        }

    }

    public boolean hasNavigationBar() {

        try {

            WebElement nav = wait.until(

                    ExpectedConditions.visibilityOfElementLocated(

                            By.className("navbar")
                    )
            );

            return nav.isDisplayed();

        } catch (Exception e) {

            return false;

        }

    }

}