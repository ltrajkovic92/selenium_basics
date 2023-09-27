package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException{

//        1.	Zadatak
//        Napisati program koji:
//        ●	Ucitava stranicu https://demoqa.com/automation-practice-form
//        ●	Popunjava formu sta stranice. Korisnik unosi podatke sa tastature za popunu forme.
//        ●	(za vezbanje) Probajte da unese i datum. Sa datumom se radi isto kao i sa obicnim inputom sa sendKeys.
//        ●	Klik na submit

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://demoqa.com/automation-practice-form");

        Scanner s = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String name = s.next();
        driver.findElement(By.id("firstName")).sendKeys(name);

        System.out.print("Enter your last name: ");
        String lastName = s.next();
        driver.findElement(By.id("lastName")).sendKeys(lastName);

        System.out.print("Enter your email: ");
        String email = s.next();
        driver.findElement(By.id("userEmail")).sendKeys(email);

        System.out.print("Gender: ");
        String gender = s.next();
        driver.findElement(By.xpath("//label[text()='"+ gender.substring(0, 1).toUpperCase() + gender.substring(1).toLowerCase() +"']")).click();

        System.out.print("Enter your number (10 digits): ");
        String number = s.next();
        driver.findElement(By.id("userNumber")).sendKeys(number);

        System.out.print("Enter subject: ");
        String subject = s.next();
        driver.findElement(By.id("subjectsInput")).sendKeys(subject);

        System.out.print("Choose hobby (Sports, Reading, Music): ");
        String hobby = s.next();
        driver.findElement(By.xpath("//label[text()='" + hobby.substring(0,1).toUpperCase() + hobby.substring(1).toLowerCase() + "']")).click();

        driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\Lazar\\Desktop\\123456.jpg");

        System.out.print("Enter your address: ");
        String address = s.next();
        driver.findElement(By.id("currentAddress")).sendKeys(address);

        driver.findElement(By.id("userForm")).submit();
        Thread.sleep(4000);

        driver.quit();
    }
}
