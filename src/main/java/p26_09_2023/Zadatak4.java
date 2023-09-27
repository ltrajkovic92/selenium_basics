package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Zadatak4 {
    public static void main(String[] args) throws InterruptedException{

//        3. Zadatak (dok ne stignemo do ovog zadatka izguglajte kako da selektujete vrednost u select elementu)
//
//        Napisati program koji ucitava stranicu https://www.ebay.com/ i bira kategoriju “Crafts”


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.ebay.com/");

        WebElement selectElement = driver.findElement(By.id("gh-cat"));
        Select categorySelect = new Select(selectElement);
//        categorySelect.selectByVisibleText("Crafts");
//        categorySelect.selectByValue("14339");
        categorySelect.selectByIndex(13);

        Thread.sleep(4000);

        driver.quit();

    }
}
