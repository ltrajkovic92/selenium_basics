package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Zadatak2 {
    public static void main(String[] args) {

//        2. Zadatak
//        Napisati program koji ucitava stranicu https://youtube.com i u search baru unosi tekste Hladno pivo i ceka da se pojavi vise od 3 rezultata iz padajuceg menija i zatim klikce na prvi.

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://www.youtube.com/");

        WebElement search = driver.findElement(By.cssSelector("input#search"));
        search.sendKeys("Hladno pivo");
        search.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("a#video-title"),2));

        driver.findElement(By.cssSelector("a#video-title")).click();

        driver.quit();
    }
}
