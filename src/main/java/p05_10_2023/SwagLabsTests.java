package p05_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import p02_10_2023.Zadatak5.Helper;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

//    Kreirati klasu SwagLabsTests https://www.saucedemo.com/
public class SwagLabsTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://www.saucedemo.com/ ";

    @BeforeClass
    public void setup () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void beforeMethode() {
        driver.navigate().to(baseUrl);
    }

//    Test #1:  Verify error is displayed when username is missing
    @Test (priority = 1)
    public void verifyErrorIsDisplayedWhenUsernameIsMissing () {
        driver.findElement(By.className("submit-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),
                "Epic sadface: Username is required",
                "The message should be 'Epic sadface: Username is required'.");
    }

//    Test #2:  Verify error is displayed when password is missing
    @Test (priority = 2)
    public void verifyErrorIsDisplayedWhenPasswordIsMissing () {
        String username = "Tjks";
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.className("submit-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),
                "Epic sadface: Password is required",
                "The message should be 'Epic sadface: Password is required'.");
    }

//    Verify error is displayed when credentials are wrong
    @Test (priority = 3)
    public void verifyErrorIsDisplayedWhenCredentialsAreWrong () {
        String username = "standard_user";
        String password = "invalidpassword";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("submit-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),
                "Epic sadface: Username and password do not match any user in this service",
        "The message should be 'Epic sadface: Username and password do not match any user in this service'.");
    }

//    Test #4:  Verify error is displayed when user is locked
    @Test (priority = 4)
    public void verifyErrorIsDisplayedWhenUserIsLocked () {
        String username = "locked_out_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("submit-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test='error']")).getText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "The message should be 'Epic sadface: Sorry, this user has been locked out.'.");
    }

//    Test #5:  Verify successful login
    @Test (priority = 5)
    public void verifySuccessfulLogin () {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("submit-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Should be redirected to inventory page after login.");

        driver.findElement(By.id("react-burger-menu-btn")).click();

        wait
                .withMessage("Menu should be visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap")));

        boolean logoutExists =
                !driver.findElements(By.id("logout_sidebar_link")).isEmpty();
        Assert.assertTrue(logoutExists, "Logout should exists.");

        driver.findElement(By.id("logout_sidebar_link")).click();

        boolean loginFormExists =
                !driver.findElements(By.className("login_wrapper")).isEmpty();

        Assert.assertTrue(loginFormExists, "Should be redirected to login page after logout.");
    }

//    Test #6:  Adding Products to Cart
    @Test(priority = 6)
    public void verifyAddingProductsToChart() {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("submit-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Should be redirected to inventory page after login.");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        wait
                .withMessage("Remove button is not visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));

        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(),
                "1","Number of products in the cart has not increased by one after Add to cart.");

    }

//    Test #7: Viewing Product Details
    @Test (priority = 7, retryAnalyzer = SwagLabsRetry.class)
    public void viewingProductDetails () {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("submit-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Url should contain '/inventory.html'");
        driver.findElement(By.id("item_4_img_link")).click();

        wait
                .withMessage("Product image is not visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_details_img_container")));
        wait
                .withMessage("Product name is not visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_details_name")));

        Assert.assertFalse(driver.findElement(By.className("inventory_details_desc"))
                        .getText().isEmpty(),
                "Product description is not visible.");

        Assert.assertFalse(driver.findElement(By.className("inventory_details_price"))
                        .getText().isEmpty(),
                "Product price is not visible.");

        wait
                .withMessage("Cart button is not visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-sauce-labs-backpack")));

    }

//    Test #8: Removing Products from Cart
    @Test (priority = 8, retryAnalyzer = SwagLabsRetry.class)
    public void removingProductsFromCart () {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("submit-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Url should contain '/inventory.html'");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(),
                "1","The number of products in cart should be 1.");

        driver.findElement(By.id("shopping_cart_container")).click();

        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 1,
                "Product is not added to cart");

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(),
                0, "Cart is not empty");

    }

//    Test #9: Product Checkout
    @Test (priority = 9, retryAnalyzer = SwagLabsRetry.class)
    public void productCheckout () {
        String username = "standard_user";
        String password = "secret_sauce";
        String checkoutName = "Pera";
        String checkoutLastName = "Peric";
        String checkoutZip = "18000";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("submit-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Url should contain '/inventory.html'");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(),
                "1","The number of products in cart should be 1.");

        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.className("checkout_button")).click();

        driver.findElement(By.id("first-name")).sendKeys(checkoutName);
        driver.findElement(By.id("last-name")).sendKeys(checkoutLastName);
        driver.findElement(By.id("postal-code")).sendKeys(checkoutZip);

        driver.findElement(By.id("continue")).click();

        Assert.assertEquals(driver.findElement(By.className("cart_quantity")).getText(), "1",
                "Cart quantity should be 1.");
        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(),
                "Sauce Labs Backpack", "Name of product should be 'Sauce Labs Backpack'");
        Assert.assertEquals(driver.findElement(By.className("inventory_item_price")).getText(),
                "$29.99", "Price should be '$29.99'");

        driver.findElement(By.cssSelector("button#finish")).click();

        Assert.assertEquals(driver.findElement(By.className("complete-header")).getText(),
                "Thank you for your order!", "Message should be 'Thank you for your order!'");

    }

//    DOMACI
//    Test #10:  Validate Social Links in Footer
    @Test
    public void validateSocialLinksInFooter() throws IOException {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("submit-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Url should contain '/inventory.html'");

        new Actions(driver)
                .scrollToElement(driver.findElement(By.className("footer")))
                .perform();

        List<WebElement> links = driver.findElements(By.cssSelector(".social a"));

        for (int i = 0; i < links.size(); i++) {
            String url = links.get(i).getAttribute("href");
            int statusCode = Helper.getHTTPResponseStatusCode(url);

            Assert.assertEquals(statusCode, 200,
                    url + "  is a Broken link - Response Status Code is not 200.");
        }
    }

    @AfterMethod
    public void afterMethod() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear();");
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
