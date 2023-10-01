package p28_09_2023.Uvod;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class ProveraPostojanja {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://demoqa.com/dynamic-properties");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//          I NACIN - TRY CATCH

//        boolean postojiElement = true;
//        WebElement btn = null;
//
//        try {
//            btn = driver.findElement(By.id("visibleAfter"));
//        } catch (Exception e) {
//        }
//
//        if (btn != null) {
//            btn.click();
//            System.out.println("Postoji element");
//        } else {
//            System.out.println("Ne postoji.");
//        }

//        II nacin

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        List<WebElement> buttons =
                driver.findElements(By.id("visibleAfter"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        if (buttons.size() == 0) {
            System.out.println("Ne postoji element");
        } else {
            System.out.println("Postoji element");
        }


//        Helper helper = new Helper();
//        boolean = helper.daLiElementPostji (By.id("lokator"));

    }
}
