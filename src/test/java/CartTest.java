import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.AssertJUnit.assertEquals;

public class CartTest {

    @Test
    public void checkCart() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.xpath("//input[@data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@data-test='login-button']")).click();

        String nameProduct = driver.findElement(By.xpath("//div[contains(text(), 'Fleece')]")).getText();
        String priceProduct = driver.findElement(
                By.xpath("//div[contains(text(), 'Fleece Jacket')]" +
                        "/ancestor::div[@class='inventory_item_description']" +
                        "//div[@class='inventory_item_price']")).getText();

        driver.findElement(
                By.xpath("//div[contains(text(), 'Fleece')]" +
                        "/ancestor::div[@class='inventory_item_description']" +
                        "//button")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        assertEquals(nameProduct, driver.findElement(
                By.xpath("//div[@class='cart_item']//div[@class='inventory_item_name']")).getText());
        assertEquals(priceProduct, driver.findElement(
                By.xpath("//div[@class='cart_item']//div[@class='inventory_item_price']")).getText());

        driver.quit();
    }
}
