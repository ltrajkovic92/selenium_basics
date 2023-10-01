package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.util.internal.ThreadExecutorMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak5 {
    public static void main(String[] args) throws InterruptedException{

//        5. Zadatak
//        Napisati program koji:
//        Ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/
//        Vrsi klik na Basic example link iz desne navigacije
//        Ceka da url sadrzi #section-basic-example

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/");

        driver.findElement(By.cssSelector("#scrollspy > ul > li:nth-child(2) > a")).click();
        wait
                .withMessage("Url ne sadrzi #section-basic-example.")
                .until(ExpectedConditions.urlContains("#section-basic-example"));

        System.out.println("Url sadrzi #section-basic-example");

        Thread.sleep(3000);

        driver.quit();
    }
}
