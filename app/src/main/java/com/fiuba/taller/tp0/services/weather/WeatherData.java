package com.fiuba.taller.tp0.services.weather;

public class WeatherData {



    private String mCityName;
    private float mDayTemperature;
    private float mNightTemperature;
    private int mHumidity;


    public WeatherData(String _cityName,float _dayTemperature, float _nightTemperature, int _humidity){
        mCityName = _cityName;
        mDayTemperature = _dayTemperature;
        mNightTemperature = _nightTemperature;
        mHumidity = _humidity;

    }
    public String getmCityName() {
        return mCityName;
    }

    public float getmDayTemperature() {
        return mDayTemperature;
    }

    public float getmNightTemperature() {
        return mNightTemperature;
    }

    public int getmHumidity() {
        return mHumidity;
    }

}
