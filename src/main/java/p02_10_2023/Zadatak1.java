package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException{

//        1.	Napisati program koji:
//            ●	Krairajte folder za fajlove u okviru projekta pod nazivom test_data
//            ●	U folder skinite i postavite proizvoljnu sliku
//            ●	Ucitava stranu https://tus.io/demo#instructions
//            ●	Skrola do dela za upload fajla // zanemarite
//            ●	Aploadujte sliku
//            ●	Cekajte da se pojava dugme za download fajla


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://tus.io/demo");

        File uploadFile = new File("test_data/123456.jpg");

//        new Actions(driver)
//                .scrollToElement(driver.findElement(By.cssSelector("#P0-0"))).perform();
        WebElement scroll = driver.findElement(By.id("P0-0"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", scroll);

        driver
                .findElement(By.id("P0-0"))
                .sendKeys(uploadFile.getAbsolutePath());

        wait
                .withMessage("File se nije upload-ovao")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a._button_gq6c0_28")));

        Thread.sleep(3000);

        driver.quit();


    }
}
