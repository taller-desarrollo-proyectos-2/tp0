package com.fiuba.taller.tp0;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.fiuba.taller.tp0.networking.DownloadCallback;
import com.fiuba.taller.tp0.networking.HttpMethodType;
import com.fiuba.taller.tp0.networking.NetworkFragment;
import com.fiuba.taller.tp0.networking.NetworkHelper;
import com.fiuba.taller.tp0.networking.NetworkObject;
import com.fiuba.taller.tp0.networking.NetworkResult;
import com.fiuba.taller.tp0.services.CustomService;
import com.fiuba.taller.tp0.services.ServiceLocator;
import com.fiuba.taller.tp0.services.weather.OpenWeatherService;
import com.fiuba.taller.tp0.services.weather.WeatherService;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private NetworkFragment mNetworkFragment = null;
    private boolean mDownloading = false;

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
        Log.i("Result", "Get Data");

        WeatherService s = ServiceLocator.get(WeatherService.class);
        s.getWeatherData("", this);
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
        return NetworkHelper.IsOnline(getBaseContext());
    }
}
