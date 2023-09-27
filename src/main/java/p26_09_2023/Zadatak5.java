package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak5 {
    public static void main(String[] args) throws InterruptedException{

//        5. Zadatak
//        Napisati program koji ucitava stranicu http://cms.demo.katalon.com/my-account/, cekira Remember me checkbox.
//        Na kraju programa proverite da li je element cekiran. Izguglajte kako mozemo da proverimo da li je element cekiran.

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://cms.demo.katalon.com/my-account/");

        WebElement checkbox = driver.findElement(By.cssSelector("#rememberme"));
        checkbox.click();
        Thread.sleep(2000);

        if (checkbox.isSelected()) {
            System.out.println("Cekirano je.");
        }

       driver.quit();
    }
}
