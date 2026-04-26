package tests;

import org.testng.annotations.Test;

import static enumUI.ProductName.FLASHLIGHT;

public class ProductTest extends BaseTest{

    @Test
    public void checkCardProduct() {
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
