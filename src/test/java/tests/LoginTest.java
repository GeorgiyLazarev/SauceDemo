package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Epic("Авторизация")
@Feature("Логин")
@Owner("Lazarev.G.A")
public class LoginTest extends BaseTest {

    @Story("Успешный вход")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("NT-T1")
    @Issue("NT-2")
    @Description("Проверяет авторизацию с корректными учетными данными")
    @Test(groups = {"smoke", "regression"},
            testName = "Проверка успешной авторизации")
    public void checkLoginWithPositiveCred() {
        loginPage.authorizationPositive();
        productPage.verifyProductsPageDisplayed();
    }

    @Story("Ошибки авторизации")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("NT-T2")
    @Issue("NT-3")
    @Description("Проверяет отображение ошибки при авторизации с пустым полем пароля")
    @Test(groups = {"smoke", "regression"},
            testName = "Проверка авторизации с пустым паролем")
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Story("Ошибки авторизации")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("NT-T3")
    @Issue("NT-4")
    @Description("Проверяет отображение ошибки при авторизации с пустым полем логина")
    @Test(groups = {"smoke", "regression"},
            testName = "Проверка авторизации с пустым логином")
    public void checkLoginWithEmptyUser() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Story("Ошибки авторизации")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("NT-T4")
    @Issue("NT-5")
    @Description("Проверяет отображение ошибки при авторизации с некорректным логином и паролем")
    @Test(groups = {"smoke", "regression"},
            testName = "Проверка авторизации с неверными учетными данными")
    public void checkLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this servic");
    }
}
