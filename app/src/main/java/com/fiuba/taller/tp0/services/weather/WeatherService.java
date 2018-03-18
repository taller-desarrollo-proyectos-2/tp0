package com.fiuba.taller.tp0.services.weather;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.fiuba.taller.tp0.services.CustomService;

public interface WeatherService extends CustomService {

    public void getWeatherData(String cityName, @NonNull WeatherDisplayer displayer, @NonNull Activity activity);
}
