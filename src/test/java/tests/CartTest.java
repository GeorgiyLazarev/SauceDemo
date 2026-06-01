package tests;

import enumUI.ProductName;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

import static enumUI.ProductName.*;

@Epic("Корзина")
@Feature("Управление корзиной")
@Owner("Lazarev.G.A")
public class CartTest extends BaseTest {

    @Story("Добавление товара")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("NT-T13")
    @Issue("NT-12")
    @Description("Проверяет, что название, цена и описание товара совпадают после добавления в корзину")
    @Test(groups = {"smoke", "regression"},
            testName = "Проверка соответствия товара при добавлении в корзину")
    public void checkCart() {

        SoftAssert softAssert = new SoftAssert();

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

    @Story("Добавление товара")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("NT-T14")
    @Issue("NT-13")
    @Description("Проверяет добавление всех доступных товаров в корзину")
    @Test(groups = {"smoke", "regression"},
            testName = "Добавление всех товаров в корзину")
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

    @Story("Добавление товара")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("NT-T15")
    @Issue("NT-14")
    @Description("Проверяет добавление товара в корзину через детальную карточку товара")
    @Test(groups = {"smoke", "regression"},
            testName = "Добавление товара через карточку товара")
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

    @Story("Удаление товара")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("NT-T16")
    @Issue("NT-15")
    @Description("Проверяет удаление товара из корзины через кнопку Remove на главной странице")
    @Test(groups = {"smoke", "regression"},
            testName = "Удаление товара через главную страницу")
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

    @Story("Удаление товара")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("NT-T17")
    @Issue("NT-16")
    @Description("Проверяет удаление товара из корзины через карточку товара на главной странице")
    @Test(groups = {"smoke", "regression"},
            testName = "Удаление товара через карточку товара")
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

    @Story("Удаление товара")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("NT-T18")
    @Issue("NT-17")
    @Description("Проверяет добавление и последующее удаление товара через детальную карточку товара")
    @Test(groups = {"smoke", "regression"},
            testName = "Удаление товара через карточку товара (детальная страница)")
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
