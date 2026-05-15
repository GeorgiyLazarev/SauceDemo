package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
            (groups = {"smoke", "regression"},
            testName = "Проверка успешной авторизации",
            description = "Тест проверяет авторизацию с корректными учетными данными")
    public void checkLoginWithPositiveCred() {
        loginPage.authorizationPositive();
        productPage.verifyProductsPageDisplayed();
    }

    @Test
            (groups = {"smoke", "regression"},
            testName = "Проверка авторизации с пустым паролем",
            description = "Тест проверяет отображение ошибки при авторизации с пустым полем пароля")
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test
            (groups = {"smoke", "regression"},
            testName = "Проверка авторизации с пустым логином",
            description = "Тест проверяет отображение ошибки при авторизации с пустым полем логина")
    public void checkLoginWithEmptyUser() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test
            (groups = {"smoke", "regression"},
            testName = "Проверка авторизации с неверными учетными данными",
            description = "Тест проверяет отображение ошибки при авторизации с некорректным логином и паролем")
    public void checkLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service");
    }
}
