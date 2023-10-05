package p02_10_2023;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Zadatak4 {
    public static void main(String[] args) throws IOException {

//        4.	Napisati program koji:
//          ●	Kreirati screenshots folder u projektu
//          ●	Ucitava stranicu https://google.com
//          ●	Kreira screenshot stranice
//          ●	Sacuvati screenshot u folderu screenshots pod imenom screenshot1.jpg
//          ●	Koristan link 1. LAKSE CE VAM BITI PREKO OVOG
//          ●	Koristan link 2
//          ●	Koristan link 3


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://www.google.com/");

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(file, new File("screenshots/screenshot1.jpg"));

    }
}
