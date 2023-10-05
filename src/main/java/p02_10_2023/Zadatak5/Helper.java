package p02_10_2023.Zadatak5;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Helper {

    public static int getHTTPResponseStatusCode(String u) throws IOException {

        URL url = new URL(u);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        return http.getResponseCode();
    }
}
