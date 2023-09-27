package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) {

        //Napisti program koji:
        //Ucitava stranicu https://s.bootsnipp.com/iframe/z80en
        //Hvata sve elemente iz tabele i stampa tekst svakog elementa. Kako da od nekog elementa procitamo tekst imate na sledecem linku
        //Ceka 5s
        //Zatvara pretrazivac
        //Stampa treba da bude kao u primeru:
        //John	Doe	john@example.com
        //Mary	Moe	mary@example.com
        //July	Dooley	july@example.com

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://s.bootsnipp.com/iframe/z80en");

        List<WebElement> header = driver.findElements(By.cssSelector("#lorem > table > thead > tr > th"));

        for (int i = 0; i < header.size(); i++) {
           String print =  header.get(i).getText();
            System.out.print(print + " ");
        }
        System.out.println();

        List<WebElement> rows = driver.findElements(By.cssSelector("#lorem > table > tbody > tr > td"));

        for (int i = 0; i < rows.size(); i++) {
            String print =  rows.get(i).getText();
            System.out.print(print + " ");
            if ((i+1) % 3 == 0) {
                System.out.println();
            }
        }

        driver.quit();
    }
}
