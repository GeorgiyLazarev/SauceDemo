package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    @DisplayName("Проверка авторизации при корректном вводе логина и пароля")
    public void checkLoginWithPositiveCred(String browser) {
        initDriver(browser);
        loginPage.authorizationPositive();
        productPage.verifyProductsPageDisplayed();
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    @DisplayName("Проверка авторизации при пустом поле пароля")
    public void checkLoginWithEmptyPassword(String browser) {
        initDriver(browser);
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    @DisplayName("Проверка авторизации при пустом логине")
    public void checkLoginWithEmptyUser(String browser) {
        initDriver(browser);
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    @DisplayName("Проверка авторизации при вводе некорректных значений")
    public void checkLoginWithNegativeCred(String browser) {
        initDriver(browser);
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service");
    }
}
