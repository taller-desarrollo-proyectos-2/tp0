package com.fiuba.taller.tp0;

import android.app.Application;
import android.content.Context;

import com.fiuba.taller.tp0.services.ServiceLocator;
import com.fiuba.taller.tp0.services.weather.OpenWeatherService;
import com.fiuba.taller.tp0.services.weather.WeatherService;

public class WeatherApplication extends Application {
    public WeatherApplication() {
        // this method fires only once per application start.
        // getApplicationContext returns null here
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // this method fires once as well as constructor
        // but also application has context here
        bindServices(getApplicationContext());
    }

    private void bindServices(Context applicationContext) {
        ServiceLocator.init(applicationContext);
        ServiceLocator.bindCustomServiceImplementation(WeatherService.class, OpenWeatherService.class);
    }
}
