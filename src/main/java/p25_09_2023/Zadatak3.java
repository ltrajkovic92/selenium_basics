package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak3 {
    public static void main(String[] args) throws InterruptedException{

//        3. Zadatak ( opcioni)
//        Napisati program koji:
//          ●	Ucitava stranicu https://demoqa.com/text-box
//          ●	Unosi informacije za 3 osobe koristeci petlju
//              ○	Full Name
//              ○	Email
//              ○	Current Address
//              ○	Permanent Address
//              ○	Klik na submit
//              ○	Ceka 2 sekunde
//              ○	Osvezava stranicu
//          ●	Zatvara pretrazivac

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://demoqa.com/text-box");

        for (int i = 0; i < 3; i++) {
            driver.findElement(By.id("userName")).sendKeys("Aleksandar Antic" + i);
            driver.findElement(By.id("userEmail")).sendKeys("aleksandar" + i + "@gmail.com");
            driver.findElement(By.id("currentAddress")).sendKeys("Nis" + i);
            driver.findElement(By.id("permanentAddress")).sendKeys("Nis" + i);

            driver.findElement(By.id("submit")).click();

            driver.navigate().refresh();
            Thread.sleep(2000);
        }
        driver.quit();
    }
}
