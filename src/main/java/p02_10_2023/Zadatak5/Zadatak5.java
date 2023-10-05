package p02_10_2023.Zadatak5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Zadatak5 {
    public static void main(String[] args) throws IOException{

//        5.	Napisati program koji:
//          ●	Ucitava stranicu https://demoqa.com/broken
//          ●	Hvata oba linka sa stranice i
//          ●	Za svaki link proverava status da je veci ili jednak od 200 i manji od 400
//          ●	Koristan link za citanje status koda nekog url-a

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/broken");

        List<WebElement> urls = driver.findElements(By.cssSelector(".row a"));

        for (int i = 0; i < urls.size(); i++) {

            int statusCode = Helper.getHTTPResponseStatusCode(urls.get(i).getAttribute("href"));

            if (statusCode >= 200 && statusCode < 400) {
                System.out.println((i+1) + ". Status code je veci/jednak od 200 i manji od 400.");
            } else if (statusCode < 200) {
                System.out.println((i+1) + ". Status code je manji od 200.");
            } else {
                System.out.println((i+1) + ". Status code je veci od 400.");
            }
        }

        driver.quit();
    }
}
