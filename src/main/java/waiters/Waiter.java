package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    public static void waitForDropdownToBeClickable(WebDriver driver, String xpath, String label) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(xpath, label))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForOptionToBeVisible(WebDriver driver, String xpath, String option) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath, option))));
    }

    public static void waitForOptionToBeClickable(WebDriver driver, String xpath, String option) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = driver.findElement(By.xpath(String.format(xpath, option)));
        wait.until(ExpectedConditions.elementToBeClickable(element));
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