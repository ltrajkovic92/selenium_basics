package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Zadatak2 {
    public static void main(String[] args) {

//        2. Zadatak
//        Napisati program koji:
//            ●	Ucitava stranu https://itbootcamp.rs/
//            ●	Misem prelazi preko Vesti iz navigacionog menija
//            ●	Ceka da se prikaze padajuci meni za Vesti
//            ●	Misem prelazi preko Kursevi iz navigacionog menija
//            ●	Ceka da se prikaze padajuci meni za Kursevi
//            ●	Misem prelazi preko Prijava i pravilnik iz navigacionog menija
//            ●	Ceka da se prikaze padajuci meni za Prijava i pravilnik
//            ●	Koristan link. Mouse over preko seleniuma https://www.selenium.dev/documentation/webdriver/actions_api/mouse/#move-to-element

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://itbootcamp.rs/");

        List<WebElement> menuItems = driver.findElements(By.cssSelector("li.dropdown>a"));
        List<WebElement> dropDownItems = driver.findElements(By.cssSelector("ul.dropdown-menu"));

        for (int i = 0; i < menuItems.size(); i++) {
            new Actions(driver)
                    .moveToElement(menuItems.get(i))
                    .perform();
            wait
                    .withMessage("Dropdown menu is not visible after mouse hover over button")
                    .until(ExpectedConditions.visibilityOf(dropDownItems.get(i)));
        }

        driver.quit();
    }
}
