package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Zadatak6 {
    public static void main(String[] args) {

//        6. Zadatak
//        Napisati program koji:
//        Ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//        Vrsi klik na Primary dugme, Secondary, Sucess, Danger
//        Ceka da broj toasts-a bude 4
//        Ispisuje poruku, toasts su prikazani
//        Ceka da broj toasts-a bude 0
//        Ispisuje poruku, toasts su se izgubili

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");

        List<WebElement> buttons = driver.findElements(By.cssSelector("button[id*=basic]"));
        List<WebElement> toasts = new ArrayList<>();
        WebElement firstToast = driver.findElement(By.id("basic-primary-example"));
        WebElement secondToast = driver.findElement(By.id("basic-secondary-example"));
        WebElement thirdToast = driver.findElement(By.id("basic-success-example"));
        WebElement fourthToast = driver.findElement(By.id("basic-danger-example"));
        toasts.add(firstToast);
        toasts.add(secondToast);
        toasts.add(thirdToast);
        toasts.add(fourthToast);

        for (int i = 0; i < 4; i++) {
            buttons.get(i).click();
        }
        wait
                .withMessage("Nisu prikazani svi toasts")
                .until(ExpectedConditions.visibilityOfAllElements(toasts));
        System.out.println("Toasts su prikazani.");

        wait
                .withMessage("Toasts se nisu izgubili.")
                .until(ExpectedConditions.invisibilityOfAllElements(toasts));

        System.out.println("Toasts su se izgubili");
        driver.quit();
    }
}
