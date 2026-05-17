package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static enumUI.ProductName.FLASHLIGHT;

@Epic("Товары")
@Feature("Карточка товара")
@Owner("Lazarev.G.A")
public class ProductTest extends BaseTest{

    @Story("Сравнение данных")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("NT-T5")
    @Issue("NT-6")
    @Description("Проверяет, что название, цена и описание товара на главной странице соответствуют данным в карточке товара")
    @Test(groups = {"smoke", "regression"},
            testName = "Проверка соответствия данных товара в карточке")
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
