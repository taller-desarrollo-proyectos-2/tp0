package com.fiuba.taller.tp0;

import android.content.Intent;
import android.os.Bundle;
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
import com.fiuba.taller.tp0.services.weather.WeatherService;
import com.fiuba.taller.tp0.utils.CalendarUtils;
import com.fiuba.taller.tp0.utils.NetworkUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

        mCityPreference = new CityPreference(this);

        if (!isOnline()) {
            showNoConnectionToast();
            // Si no hay coneccion no carga datos.
            return;
        }

        final ListView milista = (ListView)findViewById(R.id.milista);

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

        updateWeatherValues();

        setActivityTittle();

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
        int cityIndexKey = mCityPreference.getPreferedCityIndexKey();
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
        int cityIndexKey = mCityPreference.getPreferedCityIndexKey();
        String cityId = weatherService.getCityId(cityIndexKey);
        weatherService.getWeatherData(cityId, this, this);
    }

    @Override
    public void displayWeatherData(List<WeatherData> weatherData) {
        ListView list1;

        String[] dias = getFiveNextDays();

        Integer[] imageId = {
                R.drawable.a10d,
                R.drawable.a02d,
                R.drawable.a03d,
                R.drawable.a04d,
                R.drawable.a13d
        };
        Integer[] imageId2 = {
                R.drawable.a01n,
                R.drawable.a10n,
                R.drawable.a11n,
                R.drawable.a50n,
                R.drawable.a04n
        };
        String[] _temp1 = {"1","2","3","4","5"};
        String[] _temp2 = {"1","3","2","4","5"};

        MainPrueba adapter1 = new MainPrueba(MainActivity.this, dias, imageId,imageId2,_temp1,_temp2);
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

    private String[] getFiveNextDays(){
        List<String> days = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 5; i++) {
            String day = CalendarUtils.getDayOfWeek(dateFormater.format(calendar.getTime()), "-");
            days.add(day);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days.toArray(new String[5]);
    }

    @Override
    public void displayException(Exception e) {
        showNoConnectionToast();
    }
}
