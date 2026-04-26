package pages;

import enumUI.ProductName;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class ProductPage extends BasePage {

    private final By PRODUCTS  = By.xpath("//span[@class='title' and text()='Products']");
    private final By BACK_TO_PRODUCTS =
            By.xpath("//div[@class='header_secondary_container']/descendant::button");
    private final By PRICE_PRODUCT_CONTAINER = By.xpath("//div[@class='inventory_details_price']");
    private final By DESCRIPTION_PRODUCT_CONTAINER =
            By.xpath("//div[@class='inventory_details_desc large_size']");
    private final By ADD_PRODUCT =By.xpath("//button[text()='Add to cart']");
    private final By REMOVE_PRODUCT =By.xpath("//button[text()='Remove']");

    private By addToCartXPath(ProductName name) {
        return By.xpath(("//div[contains(text(), '%s')]" +
                "/ancestor::div[@class='inventory_item_description']" +
                "//button[text()='Add to cart']").formatted(name));
    }

    private By removeToCartXPath(ProductName name) {
        return By.xpath(("//div[contains(text(), '%s')]" +
                "/ancestor::div[@class='inventory_item_description']" +
                "//button[text()='Remove']").formatted(name));
    }

    private By descriptionProductXPath(ProductName name) {
        return By.xpath(("//div[contains(text(), '%s')]" +
                "/ancestor::div[@class='inventory_item_description']" +
                "//div[@class='inventory_item_desc']").formatted(name));
    }

    private By nameProductXPath(ProductName name) {
        return By.xpath("//div[contains(text(), '%s')]".formatted(name).trim());
    }

    private By priceProductXPath(ProductName name) {
        return By.xpath(("//div[contains(text(), '%s')]" +
                "/ancestor::div[@class='inventory_item_description']" +
                "//div[@class='inventory_item_price']").formatted(name));
    }

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }

    @Step("Получение наименования товара {0}")
    public String getNameProduct(ProductName name) {
        return driver.findElement(nameProductXPath(name)).getText();
    }

    @Step("Получение цены товара {0} на главной странице")
    public String getPiceProduct(ProductName name) {
        return driver.findElement(priceProductXPath(name)).getText();
    }

    @Step("Получение цены товара {0} в карточке товара")
    public String getPiceProductContainer() {
        return driver.findElement(PRICE_PRODUCT_CONTAINER).getText();
    }

    @Step("Открыть карточку товара {0}")
    public ProductPage openProductCard(ProductName name) {
        driver.findElement(nameProductXPath(name)).click();
        return this;
    }

    @Step("Добавить товар {0} в корзину")
    public ProductPage addToCartClick(ProductName name) {
        driver.findElement(addToCartXPath(name)).click();
        return this;
    }

    @Step("Добавить товар в корзину")
    public ProductPage addProductToCartClick() {
        driver.findElement(ADD_PRODUCT).click();
        return this;
    }

    @Step("Проверка отображения кнопки добавления в корзину")
    public ProductPage verifyAddProductToCart() {
        assertTrue(isVisible(ADD_PRODUCT, 2), "Кнопка \"Add to Cart\" не отображается");
        return this;
    }

    @Step("Проверка отображения кнопки \"Удалить\"")
    public ProductPage verifyRemoveProductButtonIsDisplayed() {
        assertTrue(isVisible(REMOVE_PRODUCT, 2), "Кнопка \"Remove\" не отображается");
        return this;
    }

    @Step("Удалить товар из корзины на странице карточки товара")
    public ProductPage removeProductInCardProduct() {
        driver.findElement(REMOVE_PRODUCT).click();
        return this;
    }

    @Step("Получение описания товара {0} на главной странице")
    public String getDescriptionProduct(ProductName name) {
        return driver.findElement(descriptionProductXPath(name)).getText();
    }

    @Step("Получение описания товара {0} в карточке товара")
    public String getDescriptionProductContainer() {
        return driver.findElement(DESCRIPTION_PRODUCT_CONTAINER).getText();
    }

    @Step("Проверка отображения кнопки добавления в корзину у товара {0}")
    public ProductPage verifyAddToStringProduct(ProductName name) {
        assertTrue(isVisible(addToCartXPath(name), 2), "Кнопка не отображается");
        return this;
    }

    @Step("Проверка отображения кнопки \"Удалить\" у товара {0}")
    public ProductPage verifyRemoveButtonIsDisplayed(ProductName name) {
        assertTrue(isVisible(removeToCartXPath(name), 2));
        return this;
    }

    @Step("Проверка перехода в карточку товара")
    public void verifyOpenProductCard() {
        assertTrue(isVisible(BACK_TO_PRODUCTS, 2),
                "Кнопка \"Назад\" не отображается");
    }

    @Step("Проверка отображения главной страницы")
    public ProductPage verifyProductsPageDisplayed() {
        assertTrue(isVisible(PRODUCTS, 2), "Главная страница с товарами не отображается");
        return this;
    }

    @Step("Удалить товар из корзины на главной странице")
    public ProductPage removeProduct(ProductName name) {
        driver.findElement(removeToCartXPath(name)).click();
        return this;
    }

    @Step("Выйти из карточки товара на главную страницу")
    public ProductPage goToMainProductsPage() {
        driver.findElement(BACK_TO_PRODUCTS).click();
        return this;
    }
}
