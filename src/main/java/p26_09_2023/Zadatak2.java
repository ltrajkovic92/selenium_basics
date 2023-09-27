package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException{

//        2. Zadatak
//        Napisti program koji:
//        Ucitava stranicu https://s.bootsnipp.com/iframe/z80en
//        Hvata sve elemente prve kolone i stampa tekst svakog elementa. Kako da od nekog elementa procitamo tekst imate na sledecem linku
//        Ceka 1s
//        Hvata sve elemente prvog reda i stampa tekst svakog elementa
//        Ceka 5s
//        Zatvara pretrazivac

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://s.bootsnipp.com/iframe/z80en");

        List<WebElement> column = driver.findElements(By.cssSelector("#lorem tbody > tr > td:nth-child(1)"));

        for (int i = 0; i < column.size(); i++) {
            String print = column.get(i).getText();
            System.out.println(print);
        }
        Thread.sleep(1000);

        List<WebElement> rows = driver.findElements(By.cssSelector("#lorem tbody > tr > td"));

        for (int i = 0; i < 3; i++) {
           String print = rows.get(i).getText();
            System.out.print(print + " ");
        }
        Thread.sleep(5000);

        driver.quit();
    }
}
