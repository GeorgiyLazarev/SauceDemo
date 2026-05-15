package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static enumUI.ProductName.FLASHLIGHT;

public class ProductTest extends BaseTest{

    @Test
            (groups = {"smoke", "regression"},
            testName = "Проверка соответствия данных товара в карточке",
            description = "Тест проверяет, что название, цена и описание товара на главной странице " +
                    "соответствуют данным в карточке товара")
    public void checkCardProduct() {
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
