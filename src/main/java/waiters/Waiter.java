package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

    public static void waitForPageLoaded(WebDriver driver, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            boolean documentReady = js.executeScript("return document.readyState").equals("complete");
            boolean noAjaxRequest = (Boolean) js.executeScript("return (typeof jQuery != 'undefined') ? jQuery.active == 0 : true");
            return documentReady && noAjaxRequest;
        });
    }

    public static void waitForButtonToBeClickable(WebDriver driver, WebElement button) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(button));
    }

    public static void waitForElementToBeVisible(WebDriver driver, String xpath, String label) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = driver.findElement(By.xpath(String.format(xpath, label)));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}