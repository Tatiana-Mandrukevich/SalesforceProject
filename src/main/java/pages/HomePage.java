package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public static final By WELCOME_TITLE = By.xpath("//*[@class = 'spotlightTitleText']");

    public HomePage(WebDriver driver) {
        super(driver);
    }
}