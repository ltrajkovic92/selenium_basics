package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Footer extends BasicPage{
    public String expectedCopyRightMessage = "© 2023 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy";
    public Footer(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getFooter () {
        return driver.findElement(By.className("footer"));
    }

    public WebElement getTwitter () {
        return driver.findElement(By.cssSelector(".social_twitter>a"));
    }
    public WebElement getFacebook () {
        return driver.findElement(By.cssSelector(".social_facebook>a"));
    }
    public WebElement getLinkedIn () {
        return driver.findElement(By.cssSelector(".social_linkedin>a"));
    }
    public WebElement getCopyRight () {
        return driver.findElement(By.className("footer_copy"));
    }
    public String getTextFromCopyRight () {
        return getCopyRight().getText();
    }

    public void scrollToFooter () {
        new Actions(driver).scrollToElement(getFooter()).perform();
    }
    public void clickOnTheTwitterButton () {
        getTwitter().click();
    }
    public void clickOnTheFacebookButton () {
        getFacebook().click();
    }
    public void clickOnTheLinkedInButton () {
        getLinkedIn().click();
    }
}
