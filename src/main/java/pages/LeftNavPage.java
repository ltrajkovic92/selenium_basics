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
    public void waitForMenuToBeInvisible () {
        wait
                .withMessage("Menu wrap is still visible after clicking on ekis button.")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("bm-item-list")));
    }

    public boolean doesLogoutButtonExist() {
        return elementExists(By.linkText("Logout"), 1);
    }

    public WebElement getLogoutLink() {
        return driver.findElement(By.linkText("Logout"));
    }

    public void clickLogoutButton() {
        getLogoutLink().click();
    }

    public int numberOfMenuOptions() {
        List<WebElement> options = driver.findElements(By.cssSelector(".bm-item-list a"));
        return options.size();
    }

    public List<WebElement> getMenuOptions() {
        return driver.findElements(By.cssSelector(".bm-item-list>a"));
    }

    public boolean isSpellingAllMenuOptionsCorrect() {
        boolean correctSpelling = false;

        List<WebElement> menuItems = getMenuOptions();

        if (menuItems.get(0).getText().equals("All Items")
                && menuItems.get(1).getText().equals("About")
                && menuItems.get(2).getText().equals("Logout")
                && menuItems.get(3).getText().equals("Reset App State")) {
            correctSpelling = true;
        }
        return correctSpelling;
    }
    public WebElement getAllItems(){
        return driver.findElement(By.linkText("All Items"));
    }
    public void clickAllItems(){
        getAllItems().click();
    }
    public WebElement getAbout () {
        return driver.findElement(By.linkText("About"));
    }
    public void clickAbout () {
        getAbout().click();
    }
    public WebElement getResetAppState () {
        return driver.findElement(By.linkText("Reset App State"));
    }
    public void clickResetAppState () {
        getResetAppState().click();
    }
    public boolean doesEkisButtonExistInMenu () {
        return elementExists(By.id ("react-burger-cross-btn"));
    }
    public WebElement getEkisButton () {
        return driver.findElement(By.id("react-burger-cross-btn"));
    }
    public void clickEkisButton () {
        getEkisButton().click();
    }

}

