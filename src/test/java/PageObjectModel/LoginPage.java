package PageObjectModel;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import util.*;

import java.util.Properties;


public class LoginPage extends BasePage {
    static Properties properties;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        properties = ConfigReader.getProperties();
    }

    By userName = By.id("login_field");
    By password = By.id("password");
    By signIn = By.name("commit");

    public void login() {

        sendKey(userName, properties.getProperty("username"));
        sendKey(password, properties.getProperty("password"));
        click(signIn);
    }

}
