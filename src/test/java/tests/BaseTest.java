package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import util.ConfigReader;
import util.Log4j;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    static Properties properties;
    protected WebDriver driver;

    @BeforeTest
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("–disable-notifications");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--allow-insecure-localhost");
        chromeOptions.addArguments("--acceptInsecureCerts");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.addArguments("--disable-extensions");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        properties = ConfigReader.initialize_Properties();
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Log4j.startLog("Test  is Starting");
    }

    @AfterClass
    public void tearDown() {
        Log4j.endLog("Test Bitti");
        driver.quit();

    }
}
