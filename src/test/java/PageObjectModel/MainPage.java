package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    By clickButtonToggle = By.xpath("//header/div[7]/details[1]/summary[1]/span[2]");
    By settingsButton = By.linkText("Settings");


    public void clickSettings() {
        click(clickButtonToggle);
        click(settingsButton);
    }
}
