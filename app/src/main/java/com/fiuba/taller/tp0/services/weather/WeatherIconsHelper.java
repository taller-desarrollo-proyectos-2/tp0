package com.fiuba.taller.tp0.services.weather;

import com.fiuba.taller.tp0.R;

public class WeatherIconsHelper {

    private static final String Clear = "Clear";
    private static final String RAIN = "Rain";
    private static final String Snow = "Snow";
    private static final String Drizzle = "Drizzle";
    private static final String Thunderstorm = "Thunderstorm";

    public static int getDayWeatherIcon(String weatherString) {
        if (weatherString.equals(Clear)) {
            return R.drawable.a01d;
        }
        if (weatherString.equals(RAIN)) {
            return R.drawable.a09d;
        }
        if (weatherString.equals(Drizzle)) {
            return R.drawable.a10d;
        }
        if (weatherString.equals(Snow)) {
            return R.drawable.a13d;
        }
        if (weatherString.equals(Thunderstorm)) {
            return R.drawable.a11d;
        }

        return R.drawable.blank;
    }

    public static int getNightWeatherIcon(String weatherString) {
        if (weatherString.equals(Clear)) {
            return R.drawable.a01n;
        }
        if (weatherString.equals(Drizzle)) {
            return R.drawable.a10n;
        }
        if (weatherString.equals(RAIN)) {
            return R.drawable.a09n;
        }
        if (weatherString.equals(Snow)) {
            return R.drawable.a13n;
        }
        if (weatherString.equals(Thunderstorm)) {
            return R.drawable.a11n;
        }

        return R.drawable.blank;
    }
}
