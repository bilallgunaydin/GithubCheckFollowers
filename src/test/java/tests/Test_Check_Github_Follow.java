package tests;

import PageObjectModel.LoginPage;
import PageObjectModel.MainPage;
import PageObjectModel.SettingsPage;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ConfigReader;
import util.Log4j;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Test_Check_Github_Follow extends BaseTest {

    SettingsPage settingsPage;
    RequestSpecification httpRequest;
    static Properties properties;

    @BeforeClass
    void createSettingsAndGithub() {
        settingsPage = new SettingsPage(driver);
        RestAssured.baseURI = "https://api.github.com/";
        httpRequest = RestAssured.given();
        properties = ConfigReader.getProperties();
    }

    LoginPage loginPage;
    MainPage mainPage;

    @Test(priority = 1)
    public void login() {
        loginPage = new LoginPage(driver);
        loginPage.login();
        Log4j.info("Login done.");
    }

    @Test(priority = 2)
    public void clickSettings() {
        mainPage = new MainPage(driver);
        Log4j.info("Github Homepage opened");
        mainPage.clickSettings();
        Log4j.info("Clicked the Settings button");
    }

    @Test(priority = 3)
    public void clickDeveloperSettings() {
        settingsPage.clickDeveloperSettings();
        Log4j.info("Clicked Developer Settings button");
    }

    @Test(priority = 4)
    public void clickPersonalAccessTokens() {
        settingsPage.clickPersonalAccessTokens();
        Log4j.info("Clicked Personal Access Tokens button");
    }

    @Test(priority = 5)
    public void clickTokensClassic() {
        settingsPage.clickTokensClassic();
        Log4j.info("Clicked Tokens Classic button");
    }

    @Test(priority = 6)
    public void clickGenerateNewTokens() {
        settingsPage.clickGenerateNewTokens();
        Log4j.info("Clicked Generate New Tokens button");
    }

    @Test(priority = 7)
    public void clickGenerateNewTokenClassic() {
        settingsPage.clickGenerateNewTokenClassic();
        Log4j.info("Clicked Generate New Token Classic button");
    }

    @Test(priority = 8)
    public void setAccessDescription() {
        settingsPage.setAccessDescription();
        Log4j.info("A description has been entered on the Note label.");
    }

    @Test(priority = 9)
    public void clickUserFollow() {
        settingsPage.clickUserFollow();
        Log4j.info("UserFollow checkbox is clicked.");
    }

    @Test(priority = 10)
    public void clickGenerateToken() {
        settingsPage.clickGenerateToken();
        Log4j.info("Clicked Generate Token button");
    }

    String token;

    @Test(priority = 11)
    public void setCreateToken() {
        token = settingsPage.setCreateToken();
        Log4j.info("The token value is defined to the variable.");
    }

    List<String> followingList;

    @Test(priority = 12)
    public void getFollowingList() {
        followingList = httpRequest.queryParam("per_page", "999999999999999999999")
                .get("/users/" + properties.getProperty("username") + "/following")
                .jsonPath()
                .get("login");

        Log4j.info("The list of people followed by the entered user has been taken.");

        Collections.sort(followingList);

        Log4j.info("Followed people are listed alphabetically from smallest to largest.");
    }

    List<String> followerList;

    @Test(priority = 13)
    public void getFollowerList() {

        followerList = httpRequest.queryParam("per_page", "999999999999999999999")
                .get("/users/" + properties.getProperty("username") + "/followers")
                .jsonPath()
                .get("login");

        Log4j.info("The list of people follower by the entered user has been taken.");

        Collections.sort(followerList);

        Log4j.info("Follower people are listed alphabetically from smallest to largest.");

    }

    @Test(priority = 14)
    public void checkFollowerAndDeleteNotFollowed() {

        for (String follow : followingList) {
            boolean check = followerList.contains(follow);
            if (!check) {
                given().auth()
                        .preemptive()
                        .oauth2(token)
                        .when()
                        .delete("user/following/" + follow)
                        .then()
                        .statusCode(204);

                Log4j.info(follow + " person " + properties.getProperty("username") + " has been removed from the follow list because she / he does not follow the person.");
            }
        }

        followingList = httpRequest.queryParam("per_page", "999999999999999999999")
                .get("/users/" + properties.getProperty("username") + "/following")
                .jsonPath()
                .get("login");

        Log4j.info("After unfollowed people were unfollowed, a list of re-followed people was created.");

        Collections.sort(followingList);

        Log4j.info("Follower people are listed alphabetically from smallest to largest.");

        followerList = httpRequest.queryParam("per_page", "999999999999999999999")
                .get("/users/" + properties.getProperty("username") + "/followers")
                .jsonPath()
                .get("login");

        Log4j.info("The list of people follower by the entered user has been taken.");

        Collections.sort(followerList);

        Log4j.info("Follower people are listed alphabetically from smallest to largest.");

    }

    @Test(priority = 15)
    public void clickRevokeAll() {
        settingsPage.clickRevokeAll();
        Log4j.info("Clicked Revoke All button");
    }

    @Test(priority = 16)
    public void setRevokeAll() {
        settingsPage.setRevokeAll();
        Log4j.info("The username value is entered to be revoke: " + properties.getProperty("username"));
    }

    @Test(priority = 17)
    public void clickAcceptRevoke() {
        settingsPage.clickAcceptRevoke();
        Log4j.info("Clicked Accept Revoke button");
    }
}
