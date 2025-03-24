package pages;

import elements.Button;
import elements.Dropdown;
import elements.Input;
import objects.Account;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waiters.Waiter;

import java.time.Duration;

import static pages.AccountPage.DATA_BY_FIELD_NAME_XPATH;

public class NewAccountModalPage extends BasePage {

    @FindBy(xpath = "//*[@name = 'SaveEdit']")
    public WebElement saveButton;

    @FindBy(name = "SaveAndNew")
    public WebElement saveAndNewButton;

    @FindBy(name = "CancelEdit")
    public WebElement cancelButton;

    public NewAccountModalPage(WebDriver driver) {
        super(driver);
    }

    public NewAccountModalPage openPage(String url) {
        driver.get(url);
        return this;
    }

    public void createNewAccount(Account account) {
        Waiter.waitForPageLoaded(driver, Duration.ofSeconds(20));
        new Input(driver, "Account Name").writeTextToInput(account.getAccountName());
        new Input(driver, "Website").writeTextToInput(account.getWebsite());
        new Dropdown(driver, "Type").selectOptionForSimpleDropdown(account.getType());
        new Input(driver, "Description").writeTextToTextarea(account.getDescription());
        new Input(driver, "Phone").writeTextToInput(account.getPhone());
        Waiter.waitForButtonToBeClickable(driver, saveButton);
        new Button(driver).clickOnButton(saveButton);
        Waiter.waitForElementToBeVisible(driver, DATA_BY_FIELD_NAME_XPATH, "Account Name");
    }
}