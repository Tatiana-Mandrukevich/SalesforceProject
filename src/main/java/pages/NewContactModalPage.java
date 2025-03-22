package pages;

import elements.Button;
import elements.Dropdown;
import elements.Input;
import objects.Account;
import objects.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waiters.Waiter;

import java.time.Duration;

import static pages.ContactPage.DATA_BY_FIELD_NAME_XPATH;

public class NewContactModalPage extends BasePage{

    private static final String warningMessage = "//*[@aria-label='Similar Records Exist']";


    @FindBy(xpath = "//*[@name = 'SaveEdit']")
    public WebElement saveButton;

    public NewContactModalPage(WebDriver driver) {
        super(driver);
    }

    public NewContactModalPage openPage(String url) {
        driver.get(url);
        return this;
    }

    public void createNewContact(Contact contact, Account account) {
        Waiter.waitForPageLoaded(driver, Duration.ofSeconds(10));
        new Input(driver, "First Name").writeTextToInput(contact.getFirstName());
        new Input(driver, "Last Name").writeTextToInput(contact.getLastName());
        new Dropdown(driver, "Account Name").selectAccountFromDropdown(account.getAccountName());
        new Input(driver, "Title").writeTextToInput(contact.getTitle());
        new Input(driver, "Description").writeTextToTextarea(contact.getDescription());
        new Input(driver, "Phone").writeTextToInput(contact.getPhone());
        new Input(driver, "Email").writeTextToInput(contact.getEmail());
        new Button(driver).clickOnButton(saveButton);
        Waiter.waitForPageLoaded(driver, Duration.ofSeconds(10));
        if (driver.findElements(By.xpath(warningMessage)).size() > 0) {
            //валидация начинается через 1-2 секунды, из-за чего не получилось придумать какой-то waiter,
            //а waiter на весь warningMessage не подходит, т.к. он появляется не всегда
            new Button(driver).clickOnButton(saveButton);
        }
        Waiter.waitForElementToBeVisible(driver, DATA_BY_FIELD_NAME_XPATH, "Name");
    }
}