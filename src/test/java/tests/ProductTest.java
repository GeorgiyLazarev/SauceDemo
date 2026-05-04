package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.asserts.SoftAssert;

import static enumUI.ProductName.FLASHLIGHT;

public class ProductTest extends BaseTest{

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    @DisplayName("Проверка соответствия товара после перехода в карточку товара")
    public void checkCardProduct(String browser) {

        initDriver(browser);

        SoftAssert softAssert = new SoftAssert();

        loginPage.authorizationPositive();

        String nameProduct = productPage.getNameProduct(FLASHLIGHT);
        String priceProduct = productPage.getPiceProduct(FLASHLIGHT);
        String descriptionProduct = productPage.getDescriptionProduct(FLASHLIGHT);

        productPage
                .openProductCard(FLASHLIGHT)
                .verifyOpenProductCard();

        String nameProductCard = productPage.getNameProduct(FLASHLIGHT);
        String priceProductCard = productPage.getPiceProductContainer();
        String descriptionProductCard = productPage.getDescriptionProductContainer();

        softAssert.assertEquals(nameProduct, nameProductCard);
        softAssert.assertEquals(priceProduct, priceProductCard);
        softAssert.assertEquals(descriptionProduct, descriptionProductCard);

        softAssert.assertAll();
    }
}
