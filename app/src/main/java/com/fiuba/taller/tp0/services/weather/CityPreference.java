package com.fiuba.taller.tp0.services.weather;

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPreference {
    private static final String KEY = "index_key";
    private static final String CITY_NAME = "prefered_city";

    private static final String DEFAULT_CITY_NAME = "Buenos Aires";
    private static final int DEFAULT_CITY_KEY = 8970;

    private SharedPreferences mSharedPrefs;

    public CityPreference(Activity activity){
        mSharedPrefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public void setPreferedCityValues(int indexKey, String cityName){
        mSharedPrefs.edit().putInt(KEY, indexKey).commit();
        mSharedPrefs.edit().putString(CITY_NAME, cityName).commit();
    }

    public String getPreferedCityName(){
        return mSharedPrefs.getString(KEY, DEFAULT_CITY_NAME);
    }

    public int getPreferedCityIndexKey() {
        return mSharedPrefs.getInt(CITY_NAME, DEFAULT_CITY_KEY);
    }
}
