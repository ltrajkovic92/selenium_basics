package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasicPage{

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean doesAddedItemsExist () {
        return elementExists(By.className("cart_item"));
    }
    public WebElement getItemTitleElement () {
        return driver.findElement(By.className("inventory_item_name"));
    }
    public String getNameFromAddedItem () {
        return getItemTitleElement().getText();
    }
    public WebElement getRemoveButton () {
        return driver.findElement(By.xpath("//button[text()='Remove']"));
    }
    public WebElement getContinueShoppingButton () {
        return driver.findElement(By.id("continue-shopping"));
    }
    public WebElement getCheckoutButton () {
        return driver.findElement(By.id("checkout"));
    }
    public boolean doesItemTitleIsPresented () {
        return elementExists(By.className("inventory_item_name"));
    }

    public boolean doesItemDescriptionIsPresented () {
        return elementExists(By.className("inventory_item_desc"));
    }
    public boolean doesItemPriceIsPresented () {
        return elementExists(By.className("inventory_item_price"));
    }
    public boolean doesItemQuantityIsPresented () {
        return elementExists(By.className("cart_quantity"));
    }
    public void waitForItemTitleToBeClickable () {
        wait
                .withMessage("Item title should be clickable.")
                .until(ExpectedConditions.elementToBeClickable(getItemTitleElement())).click();
    }
    public void waitForRemoveButtonToBeVisible () {
        wait
                .withMessage("Remove button is not visible in the cart.")
                .until(ExpectedConditions.visibilityOf(getRemoveButton()));
    }
    public void clickOnRemoveButton () {
        getRemoveButton().click();
    }
    public void clickOnContinueShoppingButton () {
        getContinueShoppingButton().click();
    }
}
