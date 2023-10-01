package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak4 {
    public static void main(String[] args) throws InterruptedException{

//        4. Zadatak
//        Napisati program koji
//        ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//        Klik Primary dugme
//        Ceka da se pojavi toasts u gBootstrap Toasts - examples & tutorialornjem desnom uglu
//        Ispisuje da se element pojavio
//        Ceka da se izgubi toasts u gornjem desnom uglu
//        Ispisuje da se elment izgubio
//        Klik na primary dugme
//        Ceka da se pojavi toasts u gornjem desnom uglu
//        Ispisuje da se element pojavio
//        Klik na x dugme iz toasts-a
//        Ceka da se element izgubi
//        Ispisuje da se element izgubio

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");

        driver.findElement(By.cssSelector("button#basic-primary-trigger")).click();
        wait
                .withMessage("Element se nije pojavio.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#basic-primary-example")));
        System.out.println("Element toasts se pojavio.");

        wait
                .withMessage("Element je i dalje vidi.")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#basic-primary-example")));
        System.out.println("Element toast se izgubio.");

        driver.findElement(By.cssSelector("button#basic-primary-trigger")).click();
        wait
                .withMessage("Element se nije pojavio.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#basic-primary-example")));
        System.out.println("Element toasts se pojavio.");

        driver.findElement(By.cssSelector("div.toast-header.toast-primary > button")).click();
        wait
                .withMessage("Element se i dalje vidi.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#basic-primary-example")));
        System.out.println("Element toast se izgubio");

        Thread.sleep(2000);

        driver.quit();
    }
}
