package com.fiuba.taller.tp0;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fiuba.taller.tp0.services.ServiceLocator;
import com.fiuba.taller.tp0.services.weather.CityPreference;
import com.fiuba.taller.tp0.services.weather.WeatherData;
import com.fiuba.taller.tp0.services.weather.WeatherService;

public class SelectCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_main);

        WeatherService s = ServiceLocator.get(WeatherService.class);
        final ListView milistacities = (ListView)findViewById(R.id.milistacities);
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s.getCities());
        milistacities.setAdapter(adapterCity);


        milistacities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                saveCityPreference(pos);
                finish();
            }
        });
    }
    public void saveCityPreference(int pos){
        CityPreference cityPrefs = new CityPreference(this);
        cityPrefs.setPreferedCityIndexKey(pos);
    }
}
