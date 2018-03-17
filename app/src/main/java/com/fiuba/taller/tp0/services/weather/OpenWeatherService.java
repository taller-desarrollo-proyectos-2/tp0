package com.fiuba.taller.tp0.services.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.fiuba.taller.tp0.R;

import javax.net.ssl.HttpsURLConnection;

public class OpenWeatherService implements WeatherService {

    private static final String OPEN_WEATHER_MAP_API_FORMAT =
            "http://api.openweathermap.org/data/2.5/weather?q=%s";

    public static JSONObject getJSON(Context context, String city){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API_FORMAT, city));
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.addRequestProperty("x-api-key", context.getString(R.string.open_weather_api_key));

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer jsonBuffer = new StringBuffer(1024);
            String tmp;
            while((tmp = reader.readLine()) != null) {
                jsonBuffer.append(tmp).append("\n");
            }
            reader.close();

            JSONObject data = new JSONObject(jsonBuffer.toString());

            // cod should be 200 if the request was successful
            if(data.getInt("cod") != 200){
                Log.e("Open Weather Error", context.getString(R.string.open_weather_failed_connection));
                return null;
            }
            return data;
        }catch(Exception e){
            Log.e("Open Weather Error", context.getString(R.string.open_weather_failed_connection));
            return null;
        }
    }
}
