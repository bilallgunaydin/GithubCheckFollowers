package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import util.ConfigReader;

import java.util.Properties;

public class SettingsPage extends BasePage {
    static Properties properties;
    public SettingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        properties = ConfigReader.getProperties();
    }



    By developerSettingsButton = By.xpath("//span[contains(text(),'Developer settings')]");

    public void clickDeveloperSettings() {
        WebElement developerSettingsButtonElement = findElement(developerSettingsButton);
        click(developerSettingsButton);
    }

    By personalAccessTokens = By.xpath("//span[contains(text(),'Personal access tokens')]");

    public void clickPersonalAccessTokens() {
        click(personalAccessTokens);
    }

    By tokensClassic = By.xpath("//span[contains(text(),'Tokens (classic)')]");

    public void clickTokensClassic() {
        click(tokensClassic);
    }

    By generateNewTokens = By.xpath("//span[contains(text(),'Generate new token')]");

    public void clickGenerateNewTokens() {
        click(generateNewTokens);
    }

    By generateNewTokenClassic = By.xpath("//div[contains(text(),'Generate new token (classic)')]");

    public void clickGenerateNewTokenClassic() {
        click(generateNewTokenClassic);
    }

    By AccessDescription = By.id("oauth_access_description");

    public void setAccessDescription() {

        sendKey(AccessDescription, properties.getProperty("Note"));
    }

    By userFollow = By.xpath("//input[@value='user:follow']");

    public void clickUserFollow() {
        click(userFollow);
    }

    By generateToken = By.xpath("//button[contains(text(),'Generate token')]");

    public void clickGenerateToken() {
        click(generateToken);
    }

    By createToken = By.id("new-oauth-token");

    public String setCreateToken() {
        return getText(createToken);
    }

    By revokeAll = By.xpath("//summary[contains(text(),'Revoke all')]");

    public void clickRevokeAll() {
        click(revokeAll);
    }

    By revokeAccess = By.xpath("//input[@id='revoke-access-confirmation']");

    public void setRevokeAll() {
        sendKey(revokeAccess, properties.getProperty("username"));
    }

    By acceptRevoke = By.xpath("//button[contains(text(),'I understand, revoke access for everything')]");

    public void clickAcceptRevoke() {
        click(acceptRevoke);
    }
}
