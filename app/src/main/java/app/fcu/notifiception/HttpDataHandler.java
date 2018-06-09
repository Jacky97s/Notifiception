package app.fcu.notifiception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpDataHandler {

    public HttpDataHandler(){

    }

    public String getHttpData(String requestURL) throws IOException {
        String response = "";
        URL url;

        url = new URL(requestURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(15000);
        connection.setConnectTimeout(15000);
        connection.setDoInput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-type", "application/x-www-form-urlencoder");

        int responseCode = connection.getResponseCode();
        if(responseCode == HttpsURLConnection.HTTP_OK) {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = br.readLine()) != null) {
                response+=line;
            }
        }
        return response;
    }
}
