package com.fiuba.taller.tp0.services.weather;

import android.content.Context;

import com.fiuba.taller.tp0.R;

public class WeatherIconsHelper {

    private static final String SUNNY = "sunny";
    private static final String RAIN = "rain";

    public static int getDayWeatherIcon(String weatherString) {
        if (weatherString.equals(SUNNY)) {
            return R.drawable.a01d;
        }
        if (weatherString.equals(RAIN)) {
            return R.drawable.a01d;
        }

        return R.drawable.a01d;
    }

    public static int getNightWeatherIcon(String weatherString) {
        if (weatherString.equals(SUNNY)) {
            return R.drawable.a01n;
        }
        if (weatherString.equals(RAIN)) {
            return R.drawable.a01n;
        }

        return R.drawable.a01d;
    }
}
