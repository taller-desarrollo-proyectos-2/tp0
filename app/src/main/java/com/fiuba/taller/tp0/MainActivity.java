package com.fiuba.taller.tp0;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fiuba.taller.tp0.networking.NetworkFragment;
import com.fiuba.taller.tp0.services.ServiceLocator;
import com.fiuba.taller.tp0.services.weather.CityPreference;
import com.fiuba.taller.tp0.services.weather.WeatherData;
import com.fiuba.taller.tp0.services.weather.WeatherDisplayer;
import com.fiuba.taller.tp0.services.weather.WeatherIconsHelper;
import com.fiuba.taller.tp0.services.weather.WeatherService;
import com.fiuba.taller.tp0.utils.CalendarUtils;
import com.fiuba.taller.tp0.utils.NetworkUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WeatherDisplayer {

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
                if (!isOnline()) {
                    showNoConnectionToast();
                    return;
                }
                updateWeatherValues();
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        if (!isOnline()) {
            showNoConnectionToast();
            // Si no hay coneccion no carga datos.
            return;
        }

        final ListView milista = (ListView)findViewById(R.id.milista);

        milista.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, getResources().getDisplayMetrics().heightPixels));

//        WeatherData lunes = new WeatherData("a",2,3,4);
//        WeatherData martes = new WeatherData("a",2,3,4);
//        WeatherData miercoles = new WeatherData("a",2,3,4);
//        WeatherData jueves = new WeatherData("a",2,3,4);
//        WeatherData viernes = new WeatherData("a",2,3,4);

        //WeatherData[] values = new WeatherData[]{lunes,martes,miercoles,jueves,viernes};
        //ArrayAdapter<WeatherData> adapter = new ArrayAdapter<WeatherData>(this, R.layout.dia_main, values);
        //String[] values = new String[]{"lunes","martes","miercoles","jueves","viernes"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        //milista.setAdapter(adapter);
        /*milista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                int item = position;
                String itemval = (String)milista.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Position: "+ item+" - Valor: "+itemval, Toast.LENGTH_LONG).show();
            }

        });*/
/*
        list=(ListView)findViewById(R.id.milista);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateWeatherValues();
        setActivityTittle();
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
        if (id == R.id.action_cities) {
            openSelectCitiesActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isOnline() {
        return NetworkUtils.IsOnline(getBaseContext());
    }

    private String getChosenCityName() {
        CityPreference cityPreference = new CityPreference(this);
        int cityIndexKey = cityPreference.getPreferedCityIndexKey();
        return getWeatherService().getCityName(cityIndexKey);
    }

    private void openSelectCitiesActivity() {
        Intent intent = new Intent(this, SelectCityActivity.class);
        startActivity(intent);
    }

    private void setActivityTittle() {
        String chosenCity = getChosenCityName();
        setTitle(chosenCity);
    }

    private WeatherService getWeatherService() {
        return ServiceLocator.get(WeatherService.class);
    }

    private void showNoConnectionToast() {
        Toast.makeText(this, R.string.message_no_connection, Toast.LENGTH_LONG).show();
    }

    private void updateWeatherValues() {
        WeatherService weatherService = getWeatherService();
        CityPreference cityPreference = new CityPreference(this);
        int cityIndexKey = cityPreference.getPreferedCityIndexKey();
        String cityId = weatherService.getCityId(cityIndexKey);
        weatherService.getWeatherData(cityId, this, this);
    }

    @Override
    public void displayWeatherData(List<WeatherData> weatherData) {
        ListView list1;

        String[] dias = getFiveNextDays(weatherData);

        Integer[] dayIconsI = getDayWeatherIcons(weatherData);
        Integer[] nightIcons = getNightWeatherIcons(weatherData);
        String[] dayTemperatures = getDayTemperatures(weatherData);
        String[] nightTemperatures = getNightTemperatures(weatherData);

        MainPrueba adapter1 = new MainPrueba(MainActivity.this, dias, dayIconsI, nightIcons, dayTemperatures, nightTemperatures);
        list1=(ListView)findViewById(R.id.milista);
        list1.setAdapter(adapter1);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void displayException(Exception e) {
        showNoConnectionToast();
    }

    private String[] getFiveNextDays(List<WeatherData> weatherDataList){
        List<String> days = new ArrayList<>();
        for (int i = 0; i < weatherDataList.size(); i++) {
            WeatherData data = weatherDataList.get(i);
            String day = CalendarUtils.getDayOfWeek(data.getDate(), "-");
            days.add(day);
        }
        return days.toArray(new String[weatherDataList.size()]);
    }

    private Integer[] getDayWeatherIcons(List<WeatherData> weatherDataList) {
        List<Integer> dayIcons = new ArrayList<>();
        for (int i = 0; i < weatherDataList.size(); i++) {
            WeatherData data = weatherDataList.get(i);
            int icon = WeatherIconsHelper.getDayWeatherIcon(data.getDayWeatherType());
            dayIcons.add(icon);
        }
        return dayIcons.toArray(new Integer[weatherDataList.size()]);
    }

    private Integer[] getNightWeatherIcons(List<WeatherData> weatherDataList) {
        List<Integer> nightIcons = new ArrayList<>();
        for (int i = 0; i < weatherDataList.size(); i++) {
            WeatherData data = weatherDataList.get(i);
            int icon = WeatherIconsHelper.getNightWeatherIcon(data.getNightWeatherType());
            nightIcons.add(icon);
        }
        return nightIcons.toArray(new Integer[weatherDataList.size()]);
    }

    private String[] getDayTemperatures(List<WeatherData> weatherDataList) {
        List<String> daysTemperatures = new ArrayList<>();
        for (int i = 0; i < weatherDataList.size(); i++) {
            WeatherData data = weatherDataList.get(i);
            double temperature = data.getDayTemperature();
            String tempString;
            if (temperature == 9999.0) {
                tempString = "-";
            } else {
                tempString = String.format("%.1f ºC", temperature);
            }
            daysTemperatures.add(tempString);
        }
        return daysTemperatures.toArray(new String[weatherDataList.size()]);
    }

    private String[] getNightTemperatures(List<WeatherData> weatherDataList) {
        List<String> nightTemperatures = new ArrayList<>();
        for (int i = 0; i < weatherDataList.size(); i++) {
            WeatherData data = weatherDataList.get(i);
            double temperature = data.getNightTemperature();
            String tempString;
            if (temperature == 9999.0) {
                tempString = "-";
            } else {
                tempString = String.format("%.1f ºC", temperature);
            }
            nightTemperatures.add(tempString);
        }
        return nightTemperatures.toArray(new String[weatherDataList.size()]);
    }
}
