package com.fiuba.taller.tp0.services.weather;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.fiuba.taller.tp0.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitiesLoader {
    private static final String CITIES_FILE_PATH = "../assets/files/cities_list.txt";
    private static final String LOGGER_TAG = "CitiesLoader";

    private Map<String, String> mCitiesLookUp = new HashMap<>();

    public void loadCities(Context context) {
        try {
            final String splitRegex = "\\s+";
            final String citySpaceToken = "_";
            final String replacement = " ";

            InputStream is = context.getResources().openRawResource(R.raw.cities_list);
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = in.readLine()) != null) {
                String[] splited = line.split(splitRegex);

                if (splited.length < 2) continue;
                String cityName = splited[0].replace(citySpaceToken, replacement);
                String cityId = splited[1];
                mCitiesLookUp.put(cityName, cityId);
            }
            Log.i(LOGGER_TAG, "Cities loaded");
        } catch (IOException e) {
            Log.e(LOGGER_TAG, "Error reading cities file. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String GetCityId(String cityName) {
        return mCitiesLookUp.get(cityName);
    }

    public List<String> getCities() {
        return new ArrayList<>(mCitiesLookUp.keySet());
    }
}
