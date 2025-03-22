package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Button {

    public Button(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickOnButton(WebElement button) {
        button.click();
    }
}