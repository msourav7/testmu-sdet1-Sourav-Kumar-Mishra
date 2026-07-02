package ai;

import org.openqa.selenium.*;
import java.util.List;

public class SelfHealingAgent {

    private final WebDriver driver;

    public SelfHealingAgent(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElement(By originalLocator,
                                  List<By> fallbackLocators) {

        try {

            return driver.findElement(originalLocator);

        } catch (Exception e) {

            System.out.println("Original locator failed.");

            for (By locator : fallbackLocators) {

                try {

                    WebElement element =
                            driver.findElement(locator);

                    System.out.println(
                            "Recovered using : "
                                    + locator
                    );

                    return element;

                }

                catch (Exception ignored) {

                }

            }

            throw new NoSuchElementException(
                    "Self-healing failed."
            );

        }

    }

}