package p03_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class KatalonLoginTests {

//  1. Zadatak
//    Kreirati klasu KatalonLoginTests za testove
//    Base url: https://cms.demo.katalon.com
//    Test #1: Visit login page from Nav bar
//    Koraci:
//    ●	Ucitati home stranicu
//    ●	Kliknuti na My account link
//    ●	Verifikovati da je naslov stranice My account – Katalon Shop
//    ●	Verifikovati da se u url-u stranice javlja /my-account
//    ●	Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//    Test #2: Check input types
//    Koraci:
//    ●	Ucitati home stranicu
//    ●	Kliknuti na My account link
//    ●	Verifikovati da  polje za unos email-a za atribu type ima vrednost text
//    ●	Verifikovati da polje za unos lozinke za atribut type ima vrednost password
//    ●	Verifikovati da checkbox Remember me za atribut type ima vrednost checkbox
//    ●	Verifikovati da je Remember me checkbox decekiran. Koristan link kako naci informaciu da li je checkbox cekiran ili ne.
//    ●	Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//    Test #3: Display error when credentials are wrong
//    Podaci:
//    ●	email: invalidemail@gmail.com
//    ●	password: invalid123
//    Koraci:
//    ●	Ucitati home stranicu
//    ●	Kliknuti na My account link
//    ●	Unesite email
//    ●	Unesite password
//    ●	Kliknite na login dugme
//    ●	Verifikovati da postoji element koji prikazuje gresku
//    ●	Verifikovati da je prikazana greska ERROR: Invalid email address
//    ●	Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//    ●	Verifikovati da smo idalje na login stranici posle greske, tako sto proveravamo da se url-u javlja /my-account
//
//    Test #4: Successful login with valid credentials
//    Podaci:
//    ●	username: customer
//    ●	password: crz7mrb.KNG3yxv1fbn
//    Koraci:
//    ●	Ucitati home stranicu
//    ●	Kliknuti na My account link
//    ●	Unesite email
//    ●	Unesite password
//    ●	Kliknite na login dugme
//    ●	Verifikovati da se pojavljuje link za logout na stranici


    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://cms.demo.katalon.com";

    @BeforeClass
    public void beforeClass () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @BeforeMethod
    public void beforeMethod (){
//        driver.manage().deleteAllCookies();
        driver.get(baseUrl);
    }

    @Test (priority = 1)
    public void visitLoginPageFromNavBar () {
        driver.findElement(By.cssSelector("li.page_item.page-item-10 > a")).click();

        wait    .withMessage("Title should be 'My account-Katalon shop'")
                .until(ExpectedConditions.titleIs("My account – Katalon Shop"));

        Assert.assertTrue(driver.getCurrentUrl().contains("my-account"),"Url should contain 'my-account'");

    }
    @Test (priority = 2)
    public void checkInputTypes () {
        driver.get("https://cms.demo.katalon.com/my-account/");


        WebElement username = driver.findElement(By.id("username"));
        Assert.assertEquals(username.getAttribute("type"), "text", "Username should be text by type.");

        WebElement password = driver.findElement(By.id("password"));
        Assert.assertEquals(password.getAttribute("type"), "password", "Password should be password by type.");

        WebElement checkbox = driver.findElement(By.id("rememberme"));
        Assert.assertEquals(checkbox.getAttribute("type"), "checkbox",
                "Remember me checkbox should be checkbox type.");

        Assert.assertFalse(checkbox.isSelected(), "Checkbox should be deselected.");
    }

    @Test (priority = 3)
    public void displayErrorWhenCredentialsAreWrong () {
        driver.findElement(By.cssSelector("li.page_item.page-item-10 > a")).click();
        driver.findElement(By.id("username")).sendKeys("invalidemail@gmail.com");
        driver.findElement(By.id("password")).sendKeys("invalid123");

        driver.findElement(By.name("login")).click();
        wait
                .withMessage("Log in error message should be visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-error > li")));

        wait
                .withMessage("Error should be ': Invalid email address.'")
                .until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector(".woocommerce-error > li")),
                        ": Invalid email address."));

        Assert.assertTrue(driver.getCurrentUrl().contains("my-account"), "Url should contain 'my-account'");
    }

    @Test (priority = 4)
    public void successfulLoginWithValidCredentials () {
        driver.findElement(By.cssSelector("li.page_item.page-item-10 > a")).click();
        driver.findElement(By.id("username")).sendKeys("customer");
        driver.findElement(By.id("password")).sendKeys("crz7mrb.KNG3yxv1fbn");
        driver.findElement(By.name("login")).click();

        wait
                .withMessage("Log out button should be visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector
                        (".woocommerce-MyAccount-navigation > ul > li:nth-child(6) > a")));
    }

    @AfterClass
    public void AfterClass () {
        driver.quit();
    }
}
