package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.Duration;
import java.util.List;

public class Zadatak4 {
    public static void main(String[] args) throws IOException{

//        4. Zadatak
//        Napisati program koji:
//        ●	Ucitava stranicu https://itbootcamp.rs/
//        ●	Skrola do slajdera na dnu stranice (u kome su slike Java, Php, Selenium, …)
//        ●	Cita sve linkove slika iz slajdera
//        ●	Proverava url svake slike, i za sve slike koje imaju status veci i jednak od 200 a manji od 300, skida i smesta u folder itbootcamp_slider u okviru projekta
//        ●	Azurirajte gitignore da ignorise itbootcamp_slider folder

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://itbootcamp.rs/");

        new Actions(driver)
                .scrollToElement(driver.findElement(By.className("slider_bkgd")))
                .perform();

        List<WebElement> imgLinks = driver.findElements(By.cssSelector(".carousel-item > img"));

        for (int i = 0; i < imgLinks.size(); i++) {
            String imgSource = imgLinks.get(i).getAttribute("src");
            int statusCode = getHTTPResponseStatusCode(imgSource);
            if(statusCode>=200 && statusCode<300){
                download(imgSource, "itbootcamp_slider/img-"+i+".png");
            }
        }

        driver.quit();
    }

    public static int getHTTPResponseStatusCode(String u) throws IOException {

        URL url = new URL(u);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        return http.getResponseCode();
    }
    private static void download (String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream output = new FileOutputStream(file);
        output.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        output.close();
        rbc.close();
    }
}
