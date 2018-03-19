package com.fiuba.taller.tp0.services.weather;

import java.util.List;

public interface WeatherDisplayer {
    public void displayWeatherData(List<WeatherData> weatherData);
    public void displayException (Exception e);
}
