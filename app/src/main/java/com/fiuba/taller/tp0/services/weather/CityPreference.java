package com.fiuba.taller.tp0.services.weather;

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPreference {
    private static final String KEY = "city_name";

    private static final String DEFAULT_CITY_NAME = "Buenos Aires";

    private SharedPreferences mSharedPrefs;

    public CityPreference(Activity activity){
        mSharedPrefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getPreferedCityName(){
        return mSharedPrefs.getString(KEY, DEFAULT_CITY_NAME);
    }

    public void setPreferedCityName(String cityName){
        mSharedPrefs.edit().putString(KEY, cityName).commit();
    }
}
