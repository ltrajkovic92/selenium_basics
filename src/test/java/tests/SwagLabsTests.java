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


    @Test (priority = 7, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheUrlOnTheCartPage() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(pageUrl.isCartPage(),
                   "After clicking on the cart button, should be redirected to the cart page.");
    }

    @Test(priority = 8, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheTitleOfCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.scrollToProductName("Sauce Labs Backpack");
        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));

        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(pageTitle.isCartTitle(),
                "Title on cart page should be " + pageTitle.cartTitle);
    }

    @Test (priority = 9, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheHeaderTitleOfTheCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(topNavPage.isHeaderTitleOfTheCartPage(),
                "Header title on cart page should be " + topNavPage.headerTitleCart);
    }

    @Test(priority = 10, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheHamburgerMenuButtonIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.waitForHamburgerButton();
    }

    @Test(priority = 11, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheCartIconIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.waitForCartIcon();
    }

    @Test(priority = 12, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheHamburgerMenuIsEnabledOnCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.clickMenuButton();

        Assert.assertTrue(topNavPage.doesHamburgerMenuIsEnabled(),
                "Hamburger menu button is not enabled.");
    }

    @Test(priority = 13, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheCartIconIsEnabledOnCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        Assert.assertTrue(topNavPage.doesShoppingCartIconIsEnabled(),
                "Shopping cart button is not enabled.");
    }

    @Test(priority = 14, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheHamburgerButtonIsWorkingOnTheCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.clickMenuButton();
        leftNavPage.waitForMenuToBeVisible();
    }

    @Test(priority = 15, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheCartIconIsWorkingOnTheCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(pageUrl.isCartPage(),
                "After clicking on the cart button, should be redirected to the cart page.");
    }

    @Test(priority = 16, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheCartIconHasCorrectNumberOfAddedItems () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.scrollToProductName("Sauce Labs Backpack");
        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));

        topNavPage.clickOnShoppingCartButton();

        Assert.assertEquals(topNavPage.getShoppingCartBadgeText(),"1",
                "The number in the cart should increase by one more product, after adding to the cart.");
    }

    @Test(priority = 17, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheSubHeaderTitleOnTheCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(topNavPage.isSubHeaderTitleOfTheCartPage(),
                "Sub-header title on cart page should be " + topNavPage.subHeaderTitleCart);
    }

    @Test (priority = 18, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheTotalNumberOfMenuOptions () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.clickMenuButton();

        Assert.assertEquals(leftNavPage.numberOfMenuOptions(),4,
                "There should be four menu options.");
    }

    @Test(priority = 19, retryAnalyzer = SwagLabsRetry.class)
    public void verifyTheSpellingOfAllOptionsInMenu () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.clickMenuButton();
        leftNavPage.waitForMenuToBeVisible();

        Assert.assertTrue(leftNavPage.isSpellingAllMenuOptionsCorrect(),
                "Menu options are not correctly spelled.");
    }

    @Test (priority = 20, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfAllItemsOptionIsWorking(){
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.clickMenuButton();

        leftNavPage.clickAllItems();

        Assert.assertTrue(pageUrl.isInventory(),
                "Should be redirected to the inventory page after clicking on all items link.");
    }

    @Test(priority = 21, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfAboutOptionIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.clickMenuButton();

        leftNavPage.clickAbout();

        Assert.assertTrue(pageUrl.isSouceLabsPage(),
                "Should be redirected to the " + pageUrl.souceLabs + " after clicking on about link.");
    }

    @Test (priority = 22, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfLogoutOptionIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.clickMenuButton();

        leftNavPage.clickLogoutButton();

        Assert.assertTrue(
                loginPage.doesUsernameInputExist(),
                "Should be redirected to login page after logout.");
    }

    @Test (priority = 23, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfResetAppStateIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));

        boolean doesBagdeExists = topNavPage.doesCartBadgeExists();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.clickMenuButton();
        leftNavPage.waitForMenuToBeVisible();
        leftNavPage.clickResetAppState();

        boolean doesBadgeExistsAfterReset = topNavPage.doesCartBadgeExists();

        Assert.assertEquals(doesBadgeExistsAfterReset,!doesBagdeExists,
                "Reset App State option is not working.");
    }

    @Test (priority = 24, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheEkisButtonIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.clickMenuButton();
        leftNavPage.waitForMenuToBeVisible();

        Assert.assertTrue(leftNavPage.doesEkisButtonExistInMenu(),
                "Ekis button should be visible in menu.");
    }

    @Test (priority = 25, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheEkisButtonIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();
        topNavPage.clickMenuButton();
        leftNavPage.waitForMenuToBeVisible();
        leftNavPage.clickEkisButton();

        leftNavPage.waitForMenuToBeInvisible();
    }

    @Test (priority = 26, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsAddedIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";
        String nameOfAddedItem = "Sauce Labs Backpack";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(cartPage.doesAddedItemsExist(),
                "There are no items in the cart.");

        Assert.assertEquals(cartPage.getNameFromAddedItem(),nameOfAddedItem,
                "The item added is not the same as the item in the cart.");
    }

    @Test (priority = 27, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsTitleIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(cartPage.doesItemTitleIsPresented(),
                "Title of the added item should be visible.");
    }

    @Test (priority = 28, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsDescriptionIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(cartPage.doesItemDescriptionIsPresented(),
                "Description of the added item should be visible.");
    }

    @Test (priority = 29, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsPriceIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(cartPage.doesItemPriceIsPresented(),
                "Price of the added item should be visible.");
    }
    @Test (priority = 30, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheQuantityOfItemIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(cartPage.doesItemQuantityIsPresented(),
                "Quantity of the added item should be visible.");
    }

    @Test (priority = 31, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsTitleIsClickable () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        cartPage.waitForItemTitleToBeClickable();
    }

    @Test (priority = 32, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheItemsTitleIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        cartPage.waitForItemTitleToBeClickable();

        Assert.assertTrue(pageUrl.isInventoryItemPage(),
                "Should be redirected to inventory item page after clicking on item title.");
    }

    @Test (priority = 33, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheRemoveButtonIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        cartPage.waitForRemoveButtonToBeVisible();
    }

    @Test (priority = 34, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheRemoveButtonIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();
        cartPage.clickOnRemoveButton();

        Assert.assertFalse(cartPage.doesAddedItemsExist(),
                "Added item is not removed.");
    }

    @Test (priority = 35, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheContinueShoppingButtonIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(cartPage.getContinueShoppingButton().isDisplayed(),
                "Continue shopping button should be visible.");
    }

    @Test (priority = 36, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheContinueShoppingButtonIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        cartPage.clickOnContinueShoppingButton();

        Assert.assertTrue(pageUrl.isInventory(),
                "Should be redirected to inventory page after clicking on continue shopping button.");
    }

    @Test (priority = 37, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheCheckoutButtonIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed(),
                "Checkout button should be visible.");
    }

    @Test (priority = 38, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfCheckoutButtonIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        inventoryPage.addItemToCart(By.id("add-to-cart-sauce-labs-backpack"));
        topNavPage.clickOnShoppingCartButton();

        cartPage.clickOnCheckoutButton();

        Assert.assertTrue(pageUrl.isCheckoutPage(),
                "Should be redirected to the checkout page.");
    }

    @Test (priority = 39, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheTwitterButtonIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();

        footer.scrollToFooter();

        Assert.assertTrue(footer.getTwitter().isDisplayed(),
                "Twitter button should be visible.");
    }

    @Test (priority = 40, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTheFacebookButtonIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUsername(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnShoppingCartButton();

        footer.scrollToFooter();

        Assert.assertTrue(footer.getFacebook().isDisplayed(),
                "Facebook button should be visible.");
    }
}
