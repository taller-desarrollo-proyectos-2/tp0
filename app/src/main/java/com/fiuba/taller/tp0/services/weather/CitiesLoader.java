package com.fiuba.taller.tp0.services.weather;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

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
    private static final String LOGGER_TAG = "CitiesLoader";

    private Map<Integer, Pair<String, String>> mCitiesLookUp = new HashMap<>();
    private List<String> mCitiesNames = new ArrayList<>();

    public void loadCities(Context context) {
        try {
            final String splitRegex = "\\s+";
            final String citySpaceToken = "_";
            final String replacement = " ";

            mCitiesLookUp.clear();
            mCitiesNames.clear();

            InputStream is = context.getResources().openRawResource(R.raw.cities_list);
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line;
            int index = 0;
            while ((line = in.readLine()) != null) {
                String[] splited = line.split(splitRegex);

                if (splited.length < 2) continue;

                String cityName = splited[0].replace(citySpaceToken, replacement);
                String cityId = splited[1];
                Pair<String, String> mapValue = new Pair<>(cityId, cityName);
                mCitiesLookUp.put(index, mapValue);
                mCitiesNames.add(cityName);
                index++;
            }
            Log.i(LOGGER_TAG, "Cities loaded");
        } catch (IOException e) {
            Log.e(LOGGER_TAG, "Error reading cities file. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String GetCityId(int key) {
        return mCitiesLookUp.get(key).first;
    }

    public String GetCityName(int key) {
        return mCitiesLookUp.get(key).second;
    }

    public List<String> getCities() {
        return mCitiesNames;
    }
}
