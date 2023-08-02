package org.nimesa;
import java.io.IOException;
import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) {
        WeatherApiClient apiClient = new WeatherApiClient();
        WeatherDataParser dataParser = new WeatherDataParser();
        Scanner scanner = new Scanner(System.in);

        int option;
        do {
            System.out.println("1. Get weather");
            System.out.println("2. Get Wind Speed");
            System.out.println("3. Get Pressure");
            System.out.println("0. Exit");
            System.out.print("Enter your choice from the above options: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter date followed by time in the specified format (YYYY-MM-DD HH:mm:ss): ");
                    String dateTemp = scanner.nextLine();
                    try {
                        String jsonData = apiClient.getWeatherData();
//                        System.out.println(jsonData);
                        double temperature = dataParser.getTemperature(jsonData, dateTemp);
                        System.out.println("Temperature on " + dateTemp + " is : " + temperature);
                    } catch (IOException e) {
                        System.err.println("Error fetching data from the API.");
                    }
                    break;
                case 2:
                    System.out.print("Enter date followed by time in the specified format (YYYY-MM-DD HH:mm:ss): ");
                    String dateWind = scanner.nextLine();
                    try {
                        String jsonData = apiClient.getWeatherData();
                        double windSpeed = dataParser.getWindSpeed(jsonData, dateWind);
                        System.out.println("Wind Speed on " + dateWind + " is : " + windSpeed);
                    } catch (IOException e) {
                        System.err.println("Error fetching data from the API.");
                    }
                    break;
                case 3:
                    System.out.print("Enter date followed by time in the specified format (YYYY-MM-DD HH:mm:ss): ");
                    String datePressure = scanner.nextLine();
                    try {
                        String jsonData = apiClient.getWeatherData();
                        double pressure = dataParser.getPressure(jsonData, datePressure);
                        System.out.println("Pressure on " + datePressure + " is : " + pressure);
                    } catch (IOException e) {
                        System.err.println("Error fetching data from the API.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting from the program.");
                    break;
                default:
                    System.out.println("Sorry, the option you entered seems like an invalid one. Please try again.");
                    break;
            }
        } while (option != 0);

        scanner.close();
    }
}
