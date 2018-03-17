package com.fiuba.taller.tp0.services.weather;

import android.app.Activity;

import com.fiuba.taller.tp0.services.CustomService;

public interface WeatherService extends CustomService {

    public void getWeatherData(String cityName, Activity activity);
}
