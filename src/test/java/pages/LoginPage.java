//by using =By + findElement
//
//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.*;
//import java.time.Duration;
//
//public class LoginPage {
//
//    WebDriver driver;
//
//    public LoginPage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    By email = By.xpath("//span[text()='Email ID']/ancestor::label//input");
//    By password = By.xpath("//span[text()='Password']/ancestor::label//input");
//    By loginBtn = By.xpath("//button[text()='Login']");
//
//    public void login(String user, String pass) {
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(user);
//        driver.findElement(password).sendKeys(pass);
//        driver.findElement(loginBtn).click();
//    }
//}



//by using pageFactory = @FindBy

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//span[text()='Email ID']/ancestor::label//input")
    WebElement email;

    @FindBy(xpath = "//span[text()='Password']/ancestor::label//input")
    WebElement password;

    @FindBy(xpath = "//button[text()='Login']")
    WebElement loginBtn;

    public void login(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
    }
}