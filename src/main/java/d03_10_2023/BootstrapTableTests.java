package d03_10_2023;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BootstrapTableTests {

//  1. Zadatak
//    Kreirati BootstrapTableTests klasu koja ima:
//    Base url: https://s.bootsnipp.com/iframe/K5yrx
//    Test #1: Edit Row
//    Podaci:
//        ●	First Name: ime polaznika
//        ●	Last Name: prezime polaznika
//        ●	Middle Name: srednje ime polanzika
//    Koraci:
//        ●	Ucitati stranu /iframe/K5yrx
//        ●	Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//        ●	Klik na Edit dugme prvog reda
//        ●	Sacekati da dijalog za Editovanje bude vidljiv
//        ●	Popuniti formu podacima.
//            ○	Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
//        ●	Klik na Update dugme
//        ●	Sacekati da dijalog za Editovanje postane nevidljiv
//        ●	Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
//        ●	Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
//        ●	Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
//        ●	Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//    Test #2: Delete Row
//    Podaci:
//        ●	First Name: ime polaznika
//        ●	Last Name: prezime polaznika
//        ●	Middle Name: srednje ime polanzika
//    Koraci:
//        ●	Ucitati stranu /iframe/K5yrx
//        ●	Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//        ●	Klik na Delete dugme prvog reda
//        ●	Sacekati da dijalog za brisanje bude vidljiv
//        ●	Klik na Delete dugme iz dijaloga
//        ●	Sacekati da dijalog za Editovanje postane nevidljiv
//        ●	Verifikovati da je broj redova u tabeli za jedan manji
//        ●	Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//    Test #3: Take a Screenshot
//    Koraci:
//        ●	Ucitati stranu  /iframe/K5yrx
//        ●	Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//        ●	Kreirati screenshot stranice.
//        ●	Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. Na putanji: screenshots/slike.png


    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://s.bootsnipp.com/iframe/K5yrx";

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
//        driver.manage().deleteAllCookies();
        driver.navigate().to(baseUrl);
        wait
                .withMessage("Title of the page is not 'Table with Edit and Update Data - Bootsnipp.com'")
                .until(ExpectedConditions.titleContains("Table with Edit and Update Data - Bootsnipp.com"));
    }
    @Test (priority = 1)
    public void editRow () {
        String firstName = "Djordje";
        String lastName = "Stevic";
        String middleName = "Djole";

        driver.findElement(By.cssSelector("#d1 .update")).click();
        wait
                .withMessage("Edit dialog is not visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#edit  .modal-dialog")));

        driver.findElement(By.cssSelector("input#fn")).clear();
        driver.findElement(By.cssSelector("input#fn")).sendKeys(firstName);

        driver.findElement(By.cssSelector("input#ln")).clear();
        driver.findElement(By.cssSelector("input#ln")).sendKeys(lastName);

        driver.findElement(By.cssSelector("input#mn")).clear();
        driver.findElement(By.cssSelector("input#mn")).sendKeys(middleName);

        driver.findElement(By.cssSelector("button#up")).click();

        wait
                .withMessage("Edit dialog is still visible")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#edit  .modal-dialog")));

        Assert.assertEquals(driver.findElement(By.id("f1")).getText(), firstName,
                "First name should be "+firstName);
        Assert.assertEquals(driver.findElement(By.id("l1")).getText(), lastName,
                "Last name should be "+lastName);
        Assert.assertEquals(driver.findElement(By.id("m1")).getText(), middleName,
                "Middle name should be "+middleName);
    }
    @Test(priority = 2)
    public void deleteRow() throws InterruptedException{
        List<WebElement> rowsNo = driver.findElements(By.cssSelector(".table>tbody>tr>td"));
        List<WebElement> rowCellsNo = driver.findElements(By.cssSelector("#d1 > td"));

        driver.findElement(By.cssSelector("#d1 .delete")).click();
        wait
                .withMessage("Delete dialog is not visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#delete  .modal-dialog")));

        driver.findElement(By.id("del")).click();
        wait
                .withMessage("Delete dialog is still visible.")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#delete  .modal-dialog")));

        List<WebElement> currentRowsNo = driver.findElements(By.cssSelector(".table>tbody>tr>td"));
        Assert.assertEquals(currentRowsNo.size(),rowsNo.size()-rowCellsNo.size(),"Row is note deleted");
    }

    @Test(priority = 3)
    public void takeAScreenshot () throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(file, new File("screenshots/slike.png"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
