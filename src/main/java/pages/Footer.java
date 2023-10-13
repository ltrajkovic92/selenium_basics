package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Footer extends BasicPage{
    public Footer(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getFooter () {
        return driver.findElement(By.className("footer"));
    }

    public WebElement getTwitter () {
        return driver.findElement(By.cssSelector(".social_linkedin>a"));
    }

    public void scrollToFooter () {
        new Actions(driver).scrollToElement(getFooter()).perform();
    }
}
