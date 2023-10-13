package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUrl extends BasicPage {
    public String inventoryPage = "/inventory.html";
    public String cartPage = "/cart.html";
    public String souceLabs = "https://saucelabs.com/";
    public String inventoryItem = "inventory-item.html";
    public String checkoutPage = "checkout-step-one.html";
    public String souceLabsTwitterAccount = "https://twitter.com/saucelabs";

    public PageUrl(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public boolean isPage (String url) {
        return driver.getCurrentUrl().contains(url);
    }

    public boolean isInventory () {
        return isPage(inventoryPage);
    }

    public boolean isCartPage () {
        return isPage(cartPage);
    }

    public boolean isSouceLabsPage () {
        return isPage(souceLabs);
    }
    public boolean isInventoryItemPage () {
        return isPage(inventoryItem);
    }
    public boolean isCheckoutPage () {
        return isPage(checkoutPage);
    }
    public boolean isSauceLabsTwitterAccount () {
        return isPage(souceLabsTwitterAccount);
    }
}
