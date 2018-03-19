package com.fiuba.taller.tp0.services.weather;

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPreference {
    private static final String KEY = "city_id";

    // Default id is Buenos Aires
    private static final String DEFAULT_CITY_ID = "3699223";

    private SharedPreferences mSharedPrefs;

    public CityPreference(Activity activity){
        mSharedPrefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getPreferedCityId(){
        return mSharedPrefs.getString(KEY, DEFAULT_CITY_ID);
    }

    public void setPreferedCityId(String cityId){
        mSharedPrefs.edit().putString(KEY, cityId).commit();
    }
}
