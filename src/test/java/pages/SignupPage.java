//before using pagefactory
//
//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.*;
//import java.time.Duration;
//
//public class SignupPage {
//
//    WebDriver driver;
//
//    public SignupPage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    By firstName = By.xpath("//span[text()='First Name']/ancestor::label//input");
//    By lastName = By.xpath("//span[text()='Last Name']/ancestor::label//input");
//    By email = By.xpath("//span[text()='Email ID']/ancestor::label//input");
//    By password = By.xpath("//span[text()='Password']/ancestor::label//input");
//    By signupBtn = By.xpath("//button[text()='Sign UP']");
//    //or By signupBtn = By.cssSelector("button[class*='btn btn-primary']");
//
//    public void signup(String f, String l, String e, String p) {
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(f);
//        driver.findElement(lastName).sendKeys(l);
//        driver.findElement(email).sendKeys(e);
//        driver.findElement(password).sendKeys(p);
//        driver.findElement(signupBtn).click();
//    }
//}


//after pagefactory
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SignupPage {

    WebDriver driver;
    WebDriverWait wait;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//span[text()='First Name']/ancestor::label//input")
    WebElement firstName;

    @FindBy(xpath = "//span[text()='Last Name']/ancestor::label//input")
    WebElement lastName;

    @FindBy(xpath = "//span[text()='Email ID']/ancestor::label//input")
    WebElement email;

    @FindBy(xpath = "//span[text()='Password']/ancestor::label//input")
    WebElement password;

    @FindBy(xpath = "//button[text()='Sign UP']")
    WebElement signupBtn;

    public void signup(String f, String l, String e, String p) {
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(f);
        lastName.sendKeys(l);
        email.sendKeys(e);
        password.sendKeys(p);
        signupBtn.click();
    }
}