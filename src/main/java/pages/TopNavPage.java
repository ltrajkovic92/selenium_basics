package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopNavPage extends BasicPage {

    public String headerTitleCart = "Swag Labs";

    public TopNavPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getMenuButton() {
        return driver.findElement(By.id("react-burger-menu-btn"));
    }
    public void waitForHamburgerButton(){
        wait.withMessage("Hamburger button is not presented")
                .until(ExpectedConditions.visibilityOf(getMenuButton()));
    }

    public void clickMenuButton() {
        getMenuButton().click();
    }
    public boolean doesHamburgerMenuIsEnabled(){
        return getMenuButton().isEnabled();
    }

    public WebElement getShoppingCartBadge() {
        return driver.findElement(By.className("shopping_cart_badge"));
    }

    public String getShoppingCartBadgeText() {
        return getShoppingCartBadge().getText();
    }

    public WebElement getShoppingCartButton() {
        return driver.findElement(By.className("shopping_cart_link"));
    }
    public void waitForCartIcon(){
        wait.withMessage("Cart icon is not presented")
                .until(ExpectedConditions.visibilityOf(getShoppingCartButton()));
    }

    public void clickOnShoppingCartButton() {
        getShoppingCartButton().click();
    }

    public boolean isHeaderTitle(String title) {
        return driver.findElement(By.className("header_label")).getText().equals(title);
    }

    public boolean isHeaderTitleOfTheCartPage() {
        return isHeaderTitle(headerTitleCart);
    }
}
