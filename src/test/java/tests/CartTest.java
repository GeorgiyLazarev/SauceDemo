package tests;

import enumUI.ProductName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

import static enumUI.ProductName.*;

public class CartTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    @DisplayName("Проверка соответствия описания товара в корзине после добавления")
    public void checkCart(String browser) {

        SoftAssert softAssert = new SoftAssert();

        initDriver(browser);
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

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    @DisplayName("Проверка удаления товара через карточку товара")
    public void testRemoveFromCartUsingProductCard(String browser) {
        initDriver(browser);
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

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    @DisplayName("Проверка добавления всех единиц товаров")
    public void addAllProductsToCart(String browser) {
        List<ProductName> products = Arrays.asList(
                BACKPACK, FLASHLIGHT, BLACK_SHIRT, FLEECE_JACKET, CHILDREN_PAJAMAS, RED_JACKET);
        initDriver(browser);
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

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    @DisplayName("Проверка удаления товара через главную страницу с товарами")
    public void deleteProductCart(String browser) {
        initDriver(browser);
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

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    @DisplayName("Проверка добавления товара в корзину через карточку товара")
    public void testAddToCartFromProductCard(String browser) {
        initDriver(browser);
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

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    @DisplayName("Проверка удаления товара в корзине")
    public void testDeleteToCartFromProductCard(String browser) {
        initDriver(browser);
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
