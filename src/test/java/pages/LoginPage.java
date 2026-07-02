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
//-------------------------------------------------
//package pages;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.*;
//import org.openqa.selenium.support.ui.*;
//
//import java.time.Duration;
//
//public class LoginPage {
//
//    WebDriver driver;
//    WebDriverWait wait;
//
//    public LoginPage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    @FindBy(xpath = "//span[text()='Email ID']/ancestor::label//input")
//    WebElement email;
//
//    @FindBy(xpath = "//span[text()='Password']/ancestor::label//input")
//    WebElement password;
//
//    @FindBy(xpath = "//button[text()='Login']")
//    WebElement loginBtn;
//
//    public void login(String user, String pass) {
//        wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(user);
//        password.sendKeys(pass);
//        loginBtn.click();
//    }
//}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import ai.SelfHealingAgent;
import java.util.Arrays;

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

    // Profile icon after login
    @FindBy(css = "img[alt='photo url']")
    WebElement profileIcon;

    // Generic error message
//    @FindBy(xpath = "//*[contains(text(),'Invalid') or contains(text(),'required')]")
//    java.util.List<WebElement> errorMessages;
    @FindBy(xpath = "//p[@class='text-red-600']")
    private java.util.List<WebElement> errorMessages;

    public void login(String user,String pass){

        wait.until(ExpectedConditions.visibilityOf(email));

        email.clear();
        password.clear();

        email.sendKeys(user);
        password.sendKeys(pass);

        SelfHealingAgent healing =
                new SelfHealingAgent(driver);

        WebElement button =
                healing.findElement(

                        By.xpath("//button[text()='Login']"),

                        Arrays.asList(

                                By.cssSelector("button"),

                                By.xpath("//button[contains(text(),'Login')]"),

                                By.xpath("//button[contains(text(),'Sign')]"),

                                By.xpath("//button[@type='submit']")

                        )

                );

        button.click();
    }

    public boolean isLoggedIn(){

        try{

            wait.until(ExpectedConditions.visibilityOf(profileIcon));

            return profileIcon.isDisplayed();

        }

        catch(Exception e){

            return false;

        }

    }

//    public boolean hasErrorMessage(){
//
//        return errorMessages.size()>0;
//
//    }
public String getErrorMessage() {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement error = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("p.text-red-600")
            )
    );

    return error.getText();
}


}