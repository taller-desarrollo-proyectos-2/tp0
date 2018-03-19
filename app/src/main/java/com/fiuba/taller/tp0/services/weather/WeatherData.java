package com.fiuba.taller.tp0.services.weather;

public class WeatherData {
    private String mCityName;
    private double mDayTemperature;
    private double mNightTemperature;
    private int mHumidity;
    private String weatherType;

    public WeatherData() {}

    public WeatherData(String _cityName,double _dayTemperature, double _nightTemperature, int _humidity){
        mCityName = _cityName;
        mDayTemperature = _dayTemperature;
        mNightTemperature = _nightTemperature;
        mHumidity = _humidity;
    }
    public String getCityName() {
        return mCityName;
    }

    public double getDayTemperature() {
        return mDayTemperature;
    }

    public double getNightTemperature() {
        return mNightTemperature;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public void setCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public void setDayTemperature(double mDayTemperature) {
        this.mDayTemperature = mDayTemperature;
    }

    public void setNightTemperature(double mNightTemperature) {
        this.mNightTemperature = mNightTemperature;
    }

    public void setHumidity(int mHumidity) {
        this.mHumidity = mHumidity;
    }
}