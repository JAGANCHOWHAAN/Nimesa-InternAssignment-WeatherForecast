package org.nimesa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiClient {

    private static final String API_KEY = "b6907d289e10d714a6e88b30761fae22";
//    https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid = b6907d289e10d714a6e88b30761fae22
    private static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=" + API_KEY;

    public String getWeatherData() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }
}
