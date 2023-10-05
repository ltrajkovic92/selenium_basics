package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Zadatak2 {
    public static void main(String[] args) {
//
//        2.	Napisati program koji:
//          ●	Ucitava stranu https://blueimp.github.io/jQuery-File-Upload/
//          ●	Uploadujte sliku
//          ●	Ceka se da se pojavi slika u listi uploadovanih fajlova
//              ○	Koristite uslov da broj elemenata bude 1.
//          ●	Klik na Start dugme u okviru item-a koji se uploadovao
//          ●	Ceka se da se pojavi delete dugme pored itema
//          ●	Klik na delete dugme pored itema
//          ●	Ceka se da se element obrise
//              ○	Koristite da broj elemenata bude 0


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        File uploadFile = new File("test_data/123456.jpg");

        driver
                .findElement(By.cssSelector("input[type='file']"))
                .sendKeys(uploadFile.getAbsolutePath());

        wait
                .withMessage("File se nije uploadovao")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait
                .withMessage("Dugme delete se nije pojavilo")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-type='DELETE']")));

        driver.findElement(By.cssSelector("button[data-type='DELETE']")).click();

        wait
                .withMessage("File nije izbrisan")
                .until(ExpectedConditions.numberOfElementsToBe(By.className("name"),0));

        driver.quit();
    }
}
