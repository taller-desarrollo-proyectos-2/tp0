package com.fiuba.taller.tp0;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fiuba.taller.tp0.networking.NetworkFragment;
import com.fiuba.taller.tp0.networking.NetworkUtils;
import com.fiuba.taller.tp0.services.ServiceLocator;
import com.fiuba.taller.tp0.services.weather.CityPreference;
import com.fiuba.taller.tp0.services.weather.OpenWeatherService;
import com.fiuba.taller.tp0.services.weather.WeatherData;
import com.fiuba.taller.tp0.services.weather.WeatherDisplayer;
import com.fiuba.taller.tp0.services.weather.WeatherService;

public class MainActivity extends AppCompatActivity implements WeatherDisplayer {

    private CityPreference mCityPreference;
    private NetworkFragment mNetworkFragment = null;
    private boolean mDownloading = false;

    private ListView list;
    //private ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sistemas);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bindServices(getApplicationContext());

        mCityPreference = new CityPreference(this);

        final ListView milista = (ListView)findViewById(R.id.milista);

        WeatherData lunes = new WeatherData("a",2,3,4);
        WeatherData martes = new WeatherData("a",2,3,4);
        WeatherData miercoles = new WeatherData("a",2,3,4);
        WeatherData jueves = new WeatherData("a",2,3,4);
        WeatherData viernes = new WeatherData("a",2,3,4);

        //WeatherData[] values = new WeatherData[]{lunes,martes,miercoles,jueves,viernes};
        //ArrayAdapter<WeatherData> adapter = new ArrayAdapter<WeatherData>(this, R.layout.dia_main, values);
        String[] values = new String[]{"lunes","martes","miercoles","jueves","viernes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        milista.setAdapter(adapter);
        /*milista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                int item = position;
                String itemval = (String)milista.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Position: "+ item+" - Valor: "+itemval, Toast.LENGTH_LONG).show();
            }

        });*/

        WeatherService s = ServiceLocator.get(WeatherService.class);
        s.getWeatherData("Paris", this, this);
//        final ListView milistacities = (ListView)findViewById(R.id.milistacities);
//        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s.getCities());
//        milistacities.setAdapter(adapterCity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            openSelectCitiesActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void bindServices(Context context)
    {
        ServiceLocator.init(context);
        ServiceLocator.bindCustomServiceImplementation(WeatherService.class, OpenWeatherService.class);
    }

    private boolean isOnline() {
        return NetworkUtils.IsOnline(getBaseContext());
    }

    private String getChosenCityId() {
        return mCityPreference.getPreferedCityId();
    }

    private void openSelectCitiesActivity() {
        Intent intent = new Intent(this, SelectCityActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayWeatherData(WeatherData weatherData) {

        //TODO:
    }

    @Override
    public void displayException(Exception e) {

    }
}
