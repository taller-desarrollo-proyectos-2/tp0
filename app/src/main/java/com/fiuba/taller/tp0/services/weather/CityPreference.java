package com.fiuba.taller.tp0.services.weather;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.fiuba.taller.tp0.R;

public class CityPreference {
    private static final String KEY = "index_key";

    //Default City is Buenos Aires
    private static final int DEFAULT_CITY_KEY = 8970;

    private SharedPreferences mSharedPrefs;

    public CityPreference(Activity activity){
        mSharedPrefs = activity.getSharedPreferences(activity.getString(R.string.prefs_file), Context.MODE_PRIVATE);
    }

    public void setPreferedCityIndexKey(int indexKey){
        mSharedPrefs.edit().putInt(KEY, indexKey).commit();
    }
    public int getPreferedCityIndexKey() {
        return mSharedPrefs.getInt(KEY, DEFAULT_CITY_KEY);
    }
}
