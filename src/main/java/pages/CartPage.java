package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasicPage{

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean doesAddedItemsExist () {
        return elementExists(By.className("cart_item"));
    }
    public String getNameFromAddedItem () {
        return driver.findElement(By.className("inventory_item_name")).getText();
    }
    public boolean doesItemTitleIsPresented () {
        return elementExists(By.className("inventory_item_name"));
    }

    public boolean doesItemDescriptionIsPresented () {
        return elementExists(By.className("inventory_item_desc"));
    }
}
