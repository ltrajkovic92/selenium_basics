package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.Duration;

public class Zadatak3 {
    public static void main(String[] args) {

//        3.	Napisati program koji
//          ●	Kreirati folder downloads folder u projektu
//          ●	URL SLIKE https://cdn.britannica.com/29/150929-050-547070A1/lion-Kenya-Masai-Mara-National-Reserve.jpg
//          ●	Sliku sacuvajte u folderu downloads pod nazivom ljuti-lav.jpg
//          ●	Koristan link za skidanje fajlova u javi
//          ●	Azurirajte gitignore da ignorise downloads folder


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            download("https://cdn.britannica.com/29/150929-050-547070A1/lion-Kenya-Masai-Mara-National-Reserve.jpg",
                    "download/ljuti-lav.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
