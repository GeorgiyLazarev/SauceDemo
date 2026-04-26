package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final String login = "standard_user";
    private final String password = "secret_sauce";

    private final By USERNAME_FIELD = By.xpath("//input[@data-test='username']");
    private final By PASSWORD_FIELD = By.xpath("//input[@data-test='password']");
    private final By LOGIN_BUTTON = By.xpath("//input[@data-test='login-button']");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public void login(String user, String password) {
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public void authorizationPositive() {
        open();
        login(login, password);
    }
}
