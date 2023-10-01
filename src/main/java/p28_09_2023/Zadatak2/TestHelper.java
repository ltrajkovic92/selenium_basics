package p28_09_2023.Zadatak2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class TestHelper {
    private WebDriver driver;

    public TestHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean elementExist (By by) {

        try {
           driver.findElement(by);
           return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean elementExistByList (By by) {
        return !driver.findElements(by).isEmpty();
    }
    public void setDefaultImplicitWait () {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void setImplicitWait (int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }
}
