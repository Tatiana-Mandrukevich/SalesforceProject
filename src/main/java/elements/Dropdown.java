package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Dropdown {
    WebDriver driver;
    String label;
    private static final String SIMPLE_DROPDOWN_XPATH = "//*[contains(text(), '%s')]/ancestor::*[contains(@slot, 'inputField')]//button";
    private static final String ACCOUNT_DROPDOWN_XPATH = "//*[contains(text(), '%s')]/ancestor::*[contains(@slot, 'inputField')]//input";
    private static final String SIMPLE_DROPDOWN_OPTION_XPATH = "//*[contains(@title, '%s')]";
    private static final String ACCOUNT_DROPDOWN_OPTION_XPATH =
            "//label[contains(text(),'Account Name')]/ancestor::lightning-grouped-combobox[contains(@class,'slds-form-element')]//*[@title='%s']";


    public Dropdown(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void selectOptionForSimpleDropdown(String option) {
        driver.findElement(By.xpath(String.format(SIMPLE_DROPDOWN_XPATH, label))).click();
        driver.findElement(By.xpath(String.format(SIMPLE_DROPDOWN_OPTION_XPATH, option))).click();
    }

    public void selectAccountFromDropdown(String option) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(String.format(ACCOUNT_DROPDOWN_XPATH, label)));
        actions.moveToElement(element).click().perform();
        driver.findElement(By.xpath(String.format(ACCOUNT_DROPDOWN_OPTION_XPATH, option))).click();
    }
}