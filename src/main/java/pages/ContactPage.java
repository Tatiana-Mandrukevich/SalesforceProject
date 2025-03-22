package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BasePage{

    public static final String DATA_BY_FIELD_NAME_XPATH = "//*[@field-label='%s']//*[@slot]";

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public String getFieldValueByName(String name) {
        return driver.findElement(By.xpath(String.format(DATA_BY_FIELD_NAME_XPATH, name))).getText();
    }

    public String getAccountNameValueByName(String name) {
        String text = driver.findElement(By.xpath(String.format(DATA_BY_FIELD_NAME_XPATH, name))).getText();
        return getSecondWord(text);
    }

    public static String getSecondWord(String text) {
        String[] words = text.split("\\s+");
        if (words.length < 2) {
            throw new IllegalArgumentException("Текст содержит менее двух слов");
        }
        return words[1];
    }
}