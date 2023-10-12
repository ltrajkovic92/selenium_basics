package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getUsernameInput () {
        return driver.findElement(By.id("user-name"));
    }
    public void clearAndTypeUsername (String username) {
        getUsernameInput().clear();
        getUsernameInput().sendKeys(username);
    }

    public WebElement getPasswordInput () {
        return driver.findElement(By.id("password"));
    }
    public void clearAndTypePassword(String password) {
        getPasswordInput().clear();
        getPasswordInput().sendKeys(password);
    }

    public WebElement getLoginButton () {
        return driver.findElement(By.id("login-button"));
    }
    public void clickLoginButton () {
        getLoginButton().click();
    }

    public String getErrorMessage () {
        return driver.findElement(By.cssSelector("[data-test='error']")).getText();
    }

    public boolean doesUsernameInputExist() {
        return elementExists(By.id("user-name"));
    }
}
