package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage extends BasicPage{

    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getProductByProductNameText(String productName) {
        String path = "//div[text()='"+ productName +"']";
        return driver.findElement(By.xpath(path));
    }

    public void scrollToProductName(String productName)  {
        new Actions(driver)
                .scrollToElement(getProductByProductNameText(productName))
                .perform();
    }

    public WebElement  getAddItemToCartButton (By by) {
        return driver.findElement(by);
    }
    public void addItemToCart(By by){
        getAddItemToCartButton(by).click();
    }
    public boolean doesRemoveButtonExists(By by){
        return elementExists(by);
    }
}
