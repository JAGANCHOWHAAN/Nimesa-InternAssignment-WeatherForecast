package org.nimesa;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class WeatherDataParser {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public double getTemperature(String jsonData, String dateStr) {
        double temperature = Double.NaN;
        try {
            JSONObject jsonResponse = new JSONObject(jsonData);
            JSONArray jsonArray = jsonResponse.getJSONArray("list");
            Date date = parseDate(dateStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Date timestamp = parseDate(jsonObject.getString("dt_txt"));
                if (timestamp != null && compareDates(timestamp, date)) {
                    JSONObject mainData = jsonObject.getJSONObject("main");
                    temperature = mainData.getDouble("temp");
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temperature;
    }

    public double getWindSpeed(String jsonData, String dateStr) {
        double windSpeed = Double.NaN;
        try {
            JSONObject jsonResponse = new JSONObject(jsonData);
            JSONArray jsonArray = jsonResponse.getJSONArray("list");
            Date date = parseDate(dateStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Date timestamp = parseDate(jsonObject.getString("dt_txt"));
                if (timestamp != null && compareDates(timestamp, date)) {
                    JSONObject windData = jsonObject.getJSONObject("wind");
                    windSpeed = windData.getDouble("speed");
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return windSpeed;
    }

    public double getPressure(String jsonData, String dateStr) {
        double pressure = Double.NaN;
        try {
            JSONObject jsonResponse = new JSONObject(jsonData);
            JSONArray jsonArray = jsonResponse.getJSONArray("list");
            Date date = parseDate(dateStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Date timestamp = parseDate(jsonObject.getString("dt_txt"));
                if (timestamp != null && compareDates(timestamp, date)) {
                    JSONObject mainData = jsonObject.getJSONObject("main");
                    pressure = mainData.getDouble("pressure");
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pressure;
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean compareDates(Date date1, Date date2) {
        return date1.getTime() == date2.getTime();
    }
}
