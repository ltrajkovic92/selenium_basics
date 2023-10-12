package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUrl extends BasicPage {
    public String inventoryPage = "/inventory.html";
    public String cartPage = "/cart.html";

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
}
