package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak4 {
    public static void main(String[] args) throws InterruptedException{

//        4.Zadatak
//          ●	Ucitati stranicu http://seleniumdemo.com/?post_type=product
//          ●	Klik na search dugme u gornjem desnom uglu
//          ●	Cekati da forma za pretragu bude vidljiva
//          ●	Uneti sledeci tekst za pretragu BDD Cucumber i ENTER
//          ●	Dohvatiti prvi rezultat pretrage i proveriti da li u nazivu sadrzi tekst koji je unet za pretragu. Ispisati odgovarajuce poruke u terminalu

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("http://seleniumdemo.com/?post_type=product");

        driver.findElement(By.cssSelector(".topbar-nav__utils a.search-toggle_btn")).click();
        wait
                .withMessage("Forma za pretragu nije vidljiva.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("search__wrapper")));

        WebElement searchInput = driver.findElement(By.cssSelector("input#s-651536d633c09"));
        searchInput.sendKeys("BDD Cucumber");
        searchInput.sendKeys(Keys.ENTER);

        WebElement element = driver.findElement(By.cssSelector("a.czr-title"));
        if (element.getText().contains("BDD Cucumber")) {
            System.out.println("Prvi rezultat pretrage u nazivu sadrzi tekst koji je unet za pretragu.");
        }
        Thread.sleep(2000);
        driver.quit();
    }
}
