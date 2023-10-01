package p28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Zadatak1 {
    public static void main(String[] args) {

//        1.Zadatak
//          ●	Ucitati stranicu https://demoqa.com/modal-dialogs
//          ●	Kliknuti na dugme Large modal
//          ●	Proverite da li se prikazao dijalog i ispisite u konzoli odgovarajuce poruke


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://demoqa.com/modal-dialogs");
        driver.findElement(By.id("showLargeModal")).click();


//        List<WebElement> dialogue = driver.findElements(By.className("modal-content"));
//
//        if (dialogue.size() == 0) {
//            System.out.println("Dijalog nije prikazan");
//        } else {
//            System.out.println("Dijalog je prikazan");
//        }
//
//        driver.quit();

        boolean isDialogueOpen = true;

        try {
            WebElement dialogue = driver.findElement(By.className("modal-content"));
        } catch (Exception e) {
            isDialogueOpen = false;
        }

        if (isDialogueOpen) {
            System.out.println("Dijalog je prikazan");
        } else {
            System.out.println("Dijalog nije prikazan");
        }

        driver.quit();

    }
}
