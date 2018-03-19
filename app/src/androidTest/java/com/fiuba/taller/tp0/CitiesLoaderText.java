package com.fiuba.taller.tp0;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.fiuba.taller.tp0.services.weather.CitiesLoader;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CitiesLoaderText {
    @Rule
    CitiesLoader mCitiesLoader;

    @Before
    public void Initialize() {
        mCitiesLoader = new CitiesLoader();
        mCitiesLoader.loadCities(InstrumentationRegistry.getContext());
    }

    @Test
    public void loadedCitiesListAreNotEmpty() {
        assertTrue(mCitiesLoader.getCities().size() > 0);
    }

    @Test
    public void loadsCorrectIdForCityBuenosAires(){
        assertEquals("3699223", mCitiesLoader.GetCityId("Buenos Aires"));
    }
}
