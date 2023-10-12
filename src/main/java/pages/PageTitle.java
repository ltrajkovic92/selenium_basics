package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageTitle extends BasicPage {

    public String cartTitle = "Swag Labs";

    public PageTitle(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isTitle (String title) {
        return driver.getTitle().equals(title);
    }

    public boolean isCartTitle () {
        return isTitle(cartTitle);
    }
}
