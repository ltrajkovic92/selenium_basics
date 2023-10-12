package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import retry.SwagLabsRetry;

public class SwagLabsTests extends BasicTest {

    @Test(priority = 1, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUsernameIsMissing () {
        String expectedErrorMessage = "Epic sadface: Username is required";

        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage,
                "The message should be " + expectedErrorMessage);
    }

    @Test(priority = 2, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenPasswordIsMissing () {
        String username = "standard_user";
        String expectedErrorMessage = "Epic sadface: Password is required";

        loginPage.clearAndTypeUsername(username);
        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage,
                "The message should be " + expectedErrorMessage);
    }

    @Test (priority = 3, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenCredentialsAreWrong () {
        String username = "standard_user";
        String password = "invalidpassword";
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage,
                "The message should be " + expectedErrorMessage);
    }

    @Test (priority = 4, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUserIsLocked () {
        String username = "locked_out_user";
        String password = "secret_sauce";
        String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage,
                "The message should be " + expectedErrorMessage);
    }

    @Test (priority = 5, retryAnalyzer = SwagLabsRetry.class)
    public void verifySuccessfulLogin () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        Assert.assertTrue(pageUrl.isInventory(),
                "Should be redirected to inventory page after login.");

        topNavPage.clickMenuButton();
        leftNavPage.waitForMenuToBeVisible();

        Assert.assertTrue(leftNavPage.doesLogoutButtonExist(),
                "Logout link should exist on menu.");

        leftNavPage.clickLogoutButton();

        Assert.assertTrue(
                loginPage.doesUsernameInputExist(),
                "Should be redirected to login page after logout.");
    }

    @Test (priority = 6, retryAnalyzer = SwagLabsRetry.class)
    public void addingProductsToCart () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        Assert.assertTrue(pageUrl.isInventory(),
                "Should be redirected to inventory page after login.");

        inventoryPage.scrollToProductName("Sauce Labs Backpack");

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));

        Assert.assertTrue(inventoryPage.doesRemoveButtonExists(By.id("remove-sauce-labs-backpack")),
                "There is no remove button");

        Assert.assertEquals(topNavPage.getShoppingCartBadgeText(),"1",
        "The number in the cart should increase by one more product, after adding to the cart.");
    }
}
