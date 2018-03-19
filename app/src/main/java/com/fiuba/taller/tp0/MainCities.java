package com.fiuba.taller.tp0;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by FernandoN on 18/03/2018.
 */

public class MainCities  extends Fragment {

    public MainCities() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cities_main, container, false);

    }

}
