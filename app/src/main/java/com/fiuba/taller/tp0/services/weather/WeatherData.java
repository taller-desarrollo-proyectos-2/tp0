package com.fiuba.taller.tp0.services.weather;

public class WeatherData {
    private String mCityName;
    private float mDayTemperature;
    private float mNightTemperature;
    private int mHumidity;

    public WeatherData() {}

    public WeatherData(String _cityName,float _dayTemperature, float _nightTemperature, int _humidity){
        mCityName = _cityName;
        mDayTemperature = _dayTemperature;
        mNightTemperature = _nightTemperature;
        mHumidity = _humidity;
    }
    public String getCityName() {
        return mCityName;
    }

    public float getDayTemperature() {
        return mDayTemperature;
    }

    public float getNightTemperature() {
        return mNightTemperature;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public void setCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public void setDayTemperature(float mDayTemperature) {
        this.mDayTemperature = mDayTemperature;
    }

    public void setNightTemperature(float mNightTemperature) {
        this.mNightTemperature = mNightTemperature;
    }

    public void setHumidity(int mHumidity) {
        this.mHumidity = mHumidity;
    }
}