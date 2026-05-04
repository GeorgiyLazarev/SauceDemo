package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

public class LocatorsTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "edge"})
    public void checkLocator(String browser) {

        initDriver(browser);

        driver.findElement(By.xpath("//input[@data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@data-test='login-button']")).click();

        //id
        driver.findElement(By.id("contents_wrapper"));
        //name
        driver.findElement(By.name("robots"));
        //classname
        driver.findElement(By.className("bm-burger-button"));
        //tagname
        driver.findElement(By.tagName("div"));
        //linktext
        driver.findElement(By.linkText("Facebook"));
        //partiallinktext
        driver.findElement(By.partialLinkText("book"));

        /**
         * XPATH
         */
        //поиск по атрибуту
        driver.findElement(By.xpath("//div[@class='app_logo']"));
        //поиск по тексту
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        //поиск по частичному совпадению атрибута
        driver.findElement(By.xpath("//button[contains(@data-test, 'backpack')]"));
        //поиск по частичному совпадению текста
        driver.findElement(By.xpath("//div[contains(text(), 'bike')]"));
        //ancestor
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']//ancestor::div[@class='inventory_item_description']"));
        //descendant
        driver.findElement(By.xpath("//div[@class='inventory_item']//descendant::div[contains(text(), 'Bike')]"));
        //following
        driver.findElements(By.xpath("//div[@class='inventory_item_label']//following::a"));
        //parent
        driver.findElement(By.xpath("//div[contains(text(), 'Onesie')]//parent::a"));
        //preceding
        driver.findElements(By.xpath("//div[contains(text(), 'Onesie')]//preceding::button"));
        //поиск элемента с условием AND
        driver.findElement(By.xpath("//div[@class='inventory_item_name ' and contains(text(), 'Bolt')]"));

        /**
         * CSS:
         * - .class
         * - .class1.class2
         * - .class1 .class2
         * - #id
         * - tagname
         *
         * - tagname.class
         * - [attribute=value]
         * - [attribute~=value]
         * - [attribute|=value]
         * - [attribute^=value]
         * - [attribute$=value]
         * - [attribute*=value]
         */
        driver.findElement(By.cssSelector(".inventory_container"));
        driver.findElements(By.cssSelector(".btn_primary.btn_inventory"));
        driver.findElement(By.cssSelector("#contents_wrapper"));
        driver.findElements(By.cssSelector("button"));
        driver.findElements(By.cssSelector("div.inventory_item"));
        driver.findElements(By.cssSelector("[class='inventory_item_price']"));
        driver.findElements(By.cssSelector("[class~='btn']"));
        driver.findElement(By.cssSelector("[lang|='en']"));
        driver.findElements(By.cssSelector("[class^='inventory']"));
        driver.findElement(By.cssSelector("[id$='backpack']"));
        driver.findElements(By.cssSelector("[id*='sauce']"));

        driver.quit();
    }
}
