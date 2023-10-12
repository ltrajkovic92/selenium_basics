package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LeftNavPage extends BasicPage {
    public LeftNavPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void waitForMenuToBeVisible() {
        wait
                .withMessage("Menu wrap is not visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap")));

    }
    public boolean doesLogoutButtonExist() {
        return elementExists(By.linkText("Logout"), 1);
    }

    public WebElement getLogoutLink() {
        return driver.findElement(By.linkText("Logout"));
    }

    public void clickLogoutButton(){
        getLogoutLink().click();
    }
    public int numberOfMenuOptions () {
        List<WebElement> options = driver.findElements(By.cssSelector(".bm-item-list a"));
        return options.size();
    }
}
