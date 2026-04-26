package tests;

import enumUI.ProductName;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static enumUI.ProductName.*;

public class CartTest extends BaseTest{

    @Test
    public void checkCart() {
        loginPage.authorizationPositive();

        String nameProduct = productPage.getNameProduct(BACKPACK);
        String priceProduct = productPage.getPiceProduct(BACKPACK);
        String descriptionProduct = productPage.getDescriptionProduct(BACKPACK);

        productPage
                .verifyAddToStringProduct(BACKPACK)
                .addToCartClick(BACKPACK)
                .verifyRemoveButtonIsDisplayed(BACKPACK);
        cartPage
                .openCart()
                .productCardDisplayed(BACKPACK);

        String nameProductCart = cartPage.getProductName(BACKPACK);
        String priceProductCart = cartPage.getPriceProduct(BACKPACK);
        String descriptionProductCart = cartPage.getDescriptionProduct(BACKPACK);

        softAssert.assertEquals(nameProduct, nameProductCart);
        softAssert.assertEquals(priceProduct, priceProductCart);
        softAssert.assertEquals(descriptionProduct, descriptionProductCart);

        softAssert.assertAll();
    }

    @Test
    public void testRemoveFromCartUsingProductCard() {
        loginPage.authorizationPositive();
        productPage
                .verifyAddToStringProduct(FLEECE_JACKET)
                .addToCartClick(FLEECE_JACKET)
                .verifyRemoveButtonIsDisplayed(FLEECE_JACKET);
        cartPage
                .openCart()
                .verifyOpenCart()
                .productCardDisplayed(FLEECE_JACKET)
                .continueShoppingClick();
        productPage
                .verifyProductsPageDisplayed()
                .verifyRemoveButtonIsDisplayed(FLEECE_JACKET)
                .removeProduct(FLEECE_JACKET);
        cartPage
                .openCart()
                .verifyOpenCart()
                .productCardAbsence(FLEECE_JACKET);
    }

    @Test
    public void addAllProductsToCart() {
        List<ProductName> products = Arrays.asList(
                BACKPACK, FLASHLIGHT, BLACK_SHIRT, FLEECE_JACKET, CHILDREN_PAJAMAS, RED_JACKET);
        loginPage.authorizationPositive();
        productPage
                .verifyAddToStringProduct(BACKPACK)
                .addToCartClick(BACKPACK)
                .verifyAddToStringProduct(FLASHLIGHT)
                .addToCartClick(FLASHLIGHT)
                .verifyAddToStringProduct(BLACK_SHIRT)
                .addToCartClick(BLACK_SHIRT)
                .verifyAddToStringProduct(FLEECE_JACKET)
                .addToCartClick(FLEECE_JACKET)
                .verifyAddToStringProduct(CHILDREN_PAJAMAS)
                .addToCartClick(CHILDREN_PAJAMAS)
                .verifyAddToStringProduct(RED_JACKET)
                .addToCartClick(RED_JACKET);
        cartPage
                .openCart()
                .verifyOpenCart()
                .productCardDisplayed(products);
    }

    @Test
    public void deleteProductCart() {
        loginPage.authorizationPositive();
        productPage
                .verifyAddToStringProduct(RED_JACKET)
                .addToCartClick(RED_JACKET)
                .verifyRemoveButtonIsDisplayed(RED_JACKET);
        cartPage
                .openCart()
                .productCardDisplayed(RED_JACKET)
                .verifyRemoveButtonIsDisplayed(RED_JACKET)
                .removeProductCart(RED_JACKET)
                .productCardAbsence(RED_JACKET);
    }

    @Test
    public void testAddToCartFromProductCard() {
        loginPage.authorizationPositive();
        productPage
                .openProductCard(CHILDREN_PAJAMAS)
                .verifyAddProductToCart()
                .addProductToCartClick()
                .verifyRemoveProductButtonIsDisplayed();
        cartPage
                .openCart()
                .productCardDisplayed(CHILDREN_PAJAMAS);
    }

    @Test
    public void testDeleteToCartFromProductCard() {
        loginPage.authorizationPositive();
        productPage
                .openProductCard(CHILDREN_PAJAMAS)
                .verifyAddProductToCart()
                .addProductToCartClick()
                .verifyRemoveProductButtonIsDisplayed();
        cartPage
                .openCart()
                .productCardDisplayed(CHILDREN_PAJAMAS)
                .continueShoppingClick();
        productPage
                .openProductCard(CHILDREN_PAJAMAS)
                .verifyRemoveProductButtonIsDisplayed()
                .removeProductInCardProduct()
                .verifyAddProductToCart()
                .goToMainProductsPage()
                .verifyProductsPageDisplayed();
        cartPage
                .openCart()
                .productCardAbsence(CHILDREN_PAJAMAS);
    }
}
