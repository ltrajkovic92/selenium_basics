package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Zadatak7 {
    public static void main(String[] args) throws InterruptedException{


//        7. Zadatak
//        Napisati program koji:
//        -	Ucitava stranicu https://tus.io/demo.html
//        -	Hvata sve linkove sa stranice
//        -	Skrola do svakog h3 elementa
//                -	Od svakog h3 elementa cita text

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://tus.io/demo.html");

        List<WebElement> anchors = driver.findElements(By.tagName("a"));

        List<WebElement> h3 = driver.findElements(By.tagName("h3"));

        for (int i = 0; i < h3.size(); i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", h3.get(i));
            System.out.println(h3.get(i).getText());
            Thread.sleep(1000);
        }

        driver.quit();
    }
}
