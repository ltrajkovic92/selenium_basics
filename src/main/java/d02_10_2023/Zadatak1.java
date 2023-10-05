package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {

//        1.Zadatak
//        Napisati program koji:
//            ●	Podesava:
//              ○	implicitno cekanje za trazenje elemenata od 10s
//              ○	implicitno cekanje za ucitavanje stranice od 10s
//              ○	eksplicitno cekanje podeseno na 10s
//            ●	Podaci:
//              ○	Potrebno je u projektu ukljuciti 4 slike:
//                  ■	front.jpg
//                  ■	left.jpg
//                  ■	right.jpg
//                  ■	back.jpg
//            ●	Koraci:
//              ○	Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
//              ○	Maksimizuje prozor
//              ○	Klik na edit ikonicu
//              ○	Klik na delete iz iskacuceg dijaloga
//              ○	Klik na Add Image dugme
//              ○	Sacekajte da se pojavi desni meni
//              ○	Uploadujte front.jpg sliku
//              ○	Sacekajte da je ispod uploada slike, broj slika 1.
//              ○	Klik na sliku
//              ○	Klik na Done dugme
//              ○	Sacekajte 2s
//              ○	Klik na Add Image dugme
//              ○	Sacekajte da se pojavi desni meni
//              ○	Uploadujte right.jpg sliku
//              ○	Sacekajte da je ispod uploada slike, broj slika 2.
//              ○	Klik na sliku
//              ○	Klik na Done dugme
//              ○	Sacekajte 2s
//              ○	Klik na Add Image dugme
//              ○	Sacekajte da se pojavi desni meni
//              ○	Uploadujte back.jpg sliku
//              ○	Sacekajte da je ispod uploada slike, broj slika 3.
//              ○	Klik na sliku
//              ○	Klik na Done dugme
//              ○	Sacekajte 2s
//              ○	Klik na Add Image dugme
//              ○	Sacekajte da se pojavi desni meni
//              ○	Uploadujte back.jpg sliku
//              ○	Sacekajte da je ispod uploada slike, broj slika 3.
//              ○	Klik na sliku
//              ○	Klik na Done dugme
//              ○	Sacekajte 2s
//              ○	Sacekajte da Next dugme bude klikljivo
//              ○	Klik na Next dugme
//              ○	Unesite tekst
//              ○	Klik na Next
//              ○	Klik na Preview
//              ○	Klik na Add to cart
//              ○	Sacekajte 5s
//              ○	Quit

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");

        List<File> imgPaths = new ArrayList<>();
        imgPaths.add(new File("test_data/test_01/front.jpg"));
        imgPaths.add(new File("test_data/test_01/right.jpg"));
        imgPaths.add(new File("test_data/test_01/left.jpg"));
        imgPaths.add(new File("test_data/test_01/back.jpg"));

        driver.findElement(By.className("edit-image")).click();
        driver.findElement(By.id("image-option-remove")).click();

        for (int i = 0; i < 4; i++) {
            driver.findElement(By.className("edit-image")).click();
            wait
                    .withMessage("Add image button is not visible")
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("imageUpload")));

            driver.findElement(By.id("imageUpload"))
                    .sendKeys(imgPaths.get(i).getAbsolutePath());
            wait
                    .withMessage("The uploaded image has not been added to the list")
                    .until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("img[id*='image-option']"), i + 1));
            Thread.sleep(1000);

            driver.findElement(By.id("image-option-0")).click();
            wait
                .withMessage("Image crop done button is not visible")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#image-crop-done-button > button")));

            driver.findElement(By.cssSelector("#image-crop-done-button > button")).click();

            Thread.sleep(2000);
        }
        wait
                .withMessage("Next button is not clickable.")
                .until(ExpectedConditions.elementToBeClickable(By.id("next-button")));
        driver.findElement(By.id("next-button")).click();

        driver.findElement(By.id("textareaID")).click();
        driver.findElement(By.id("textareaID")).sendKeys("Puppies");

        Thread.sleep(1000);
        driver.findElement(By.id("next-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("next-button")).click();

        for (int i = 0; i < 4; i++) {
            new Actions(driver)
                    .clickAndHold(driver.findElement(By.id("input-container")))
                    .moveByOffset(100, -12)
                    .release()
                    .perform();
            Thread.sleep(500);
        }

        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("next-button")).click();

        Thread.sleep(5000);
        driver.quit();
    }
}
