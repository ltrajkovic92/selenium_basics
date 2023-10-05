package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Zadatak5 {
    public static void main(String[] args) {

//        5.Zadatak (za vezbanje)
//        Napisati program koji:
//            ●	Ucitava stranicu https://blueimp.github.io/jQuery-File-Upload/
//            ●	Uploaduje sve cetiri slike odjenom (slike iz prvog zadatka)
//            ●	Ceka da se prikazu 4 item-a uploada
//            ●	Klik na upload
//            ●	Ceka da se upload zavrsi


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        List<File> imgPaths = new ArrayList<>();
        imgPaths.add(new File("test_data/test_01/front.jpg"));
        imgPaths.add(new File("test_data/test_01/right.jpg"));
        imgPaths.add(new File("test_data/test_01/left.jpg"));
        imgPaths.add(new File("test_data/test_01/back.jpg"));

        for (int i = 0; i < imgPaths.size(); i++) {
            driver
                    .findElement(By.cssSelector("input[type='file']"))
                    .sendKeys(imgPaths.get(i).getAbsolutePath());
        }
        wait
                .withMessage("Not all uploaded images are visible in the list.")
                .until(ExpectedConditions.numberOfElementsToBe(By.className("preview"), imgPaths.size()));

        driver.findElement(By.cssSelector("[type='submit']")).click();
        wait
                .withMessage("Progress bar has not reached 100%.");

        driver.quit();
    }
}
