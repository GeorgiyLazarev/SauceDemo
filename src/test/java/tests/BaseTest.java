package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.TestListener;

import java.util.HashMap;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    @Description("Настройка браузера")
    public void initDriver(@Optional("chrome") String browser, ITestContext iTestContext) {
        switch (browser.toLowerCase()) {
            case "chrome" -> driver = createChromeDriver();
            case "firefox" -> driver = createFirefoxDriver();
            case "edge" -> driver = createEdgeDriver();
            default -> throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
        }

        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);

        iTestContext.setAttribute("driver", driver);
    }

    private WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        return new ChromeDriver(options);
    }

    private WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDawn() {
        driver.quit();
    }
}
