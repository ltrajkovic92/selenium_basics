package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak5 {
    public static void main(String[] args) throws InterruptedException{

//        5. Zadatak
//          ●	Ucitati stranicu http://seleniumdemo.com/?product=bdd-cucumber
//          ●	Klik na korpu iz gornjeg desnog ugla
//          ●	Sacekati da naslov stranice bude Cart – Selenium Demo Page
//          ●	Proveriti da li element koji prikazuje stanje korpe ima tekst Your cart is currently empty.

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://seleniumdemo.com/?product=bdd-cucumber");

        driver.findElement(By.cssSelector("div.topbar-nav__utils a.cart-contents")).click();

        wait.until(ExpectedConditions.titleIs("Cart – Selenium Demo Page"));

        WebElement cart = null;
        try {
            cart= driver.findElement(By.cssSelector(".cart-empty"));
        } catch (Exception e) {}

        if (cart != null) {
            System.out.println("Cart is empty.");
        }

        driver.quit();
    }
}
