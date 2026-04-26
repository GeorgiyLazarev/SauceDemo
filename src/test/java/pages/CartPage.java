package pages;

import enumUI.ProductName;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class CartPage extends BasePage {

    private final By OPEN_CART = By.xpath("//a[@class='shopping_cart_link']");
    private final By MY_CART = By.xpath("//span[@class='title' and text()='Your Cart']");
    private final By CONTINUE_SHOPPING = By.xpath("//button[text()='Continue Shopping']");

    private By nameProductXPath(ProductName name) {
        return By.xpath("//div[contains(text(), '%s')]".formatted(name).trim());
    }

    private By priceProductXPath(ProductName name) {
        return By.xpath(("//div[contains(text(), '%s')]" +
                "/ancestor::div[@class='cart_item_label']" +
                "//div[@class='inventory_item_price']").formatted(name));
    }

    private By descriptionProductXPath(ProductName name) {
        return By.xpath(("//div[contains(text(), '%s')]" +
                "/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_desc']").formatted(name));
    }

    private By removeToCartXPath(ProductName name) {
        return By.xpath(("//div[contains(text(), '%s')]" +
                "/ancestor::div[@class='cart_item']" +
                "//button[text()='Remove']").formatted(name));
    }

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть корзину")
    public CartPage openCart() {
        driver.findElement(OPEN_CART).click();
        return this;
    }

    @Step("Получение наименования товара {0}")
    public String getProductName(ProductName name) {
        return driver.findElement(nameProductXPath(name)).getText();
    }

    @Step("Получение описания товара {0}")
    public String getDescriptionProduct(ProductName name) {
        return driver.findElement(descriptionProductXPath(name)).getText();
    }

    @Step("Получение цены товара {0}")
    public String getPriceProduct(ProductName name) {
        return driver.findElement(priceProductXPath(name)).getText();
    }

    @Step("Проверка отображения карточки товара {0} в корзине")
    public CartPage productCardDisplayed(ProductName name) {
        assertTrue(isVisible(nameProductXPath(name), 2),
                "Товара с данным наименованием %s нет в корзине".formatted(name));
        return this;
    }

    @Step("Проверка отображения карточек товаров {0} в корзине")
    public CartPage productCardDisplayed(List<ProductName> products) {
        for (ProductName product : products) {
            assertTrue(isVisible(nameProductXPath(product), 2),
                    "Товар '%s' отсутствует в корзине".formatted(product));
        }
        return this;
    }

    @Step("Проверка отсутствия карточки товара {0} в корзине")
    public void productCardAbsence(ProductName name) {
        assertTrue(isNotVisible(nameProductXPath(name), 2),
                "Товара с данным наименованием %s нет в корзине".formatted(name));
    }

    @Step("Проверка отображения кнопки \"Удалить\" у товара {0}")
    public CartPage verifyRemoveButtonIsDisplayed(ProductName name) {
        assertTrue(isVisible(removeToCartXPath(name), 2));
        return this;
    }

    @Step("Удалить товар из корзины")
    public CartPage removeProductCart(ProductName name) {
        driver.findElement(removeToCartXPath(name)).click();
        return this;
    }

    @Step("Проверка открытия корзины")
    public CartPage verifyOpenCart() {
        assertTrue(isVisible(MY_CART, 2));
        return this;
    }

    @Step("Перейти обратно на страницу товаров")
    public CartPage continueShoppingClick() {
        driver.findElement(CONTINUE_SHOPPING).click();
        return this;
    }
}
