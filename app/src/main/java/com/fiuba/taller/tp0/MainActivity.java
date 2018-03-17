package com.fiuba.taller.tp0;

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
import com.fiuba.taller.tp0.networking.NetworkObject;
import com.fiuba.taller.tp0.networking.NetworkResult;
import com.fiuba.taller.tp0.services.ServiceLocator;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements DownloadCallback<NetworkResult> {

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

        Log.i("Result", "Get Data");
        getData("");
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

    private void loadServices(Context context)
    {
        ServiceLocator.init(context);

    }

    // DownloadCallback implementation

    @Override
    public void onResponseReceived(NetworkResult result) {
        // update UI
        Log.i("Result", result.toString());
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
    }

    @Override
    public void onFinishDownloading() {
        if (mNetworkFragment != null) {
            mNetworkFragment.cancelDownload();
        }
        mDownloading = false;
    }

    private boolean isOnline() {
        NetworkInfo networkInfo = getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    // get the data from server using HTTP.
    private void getData(String cityName) {
        if (mNetworkFragment == null) {
            NetworkObject networkObject = createRequestNetworkObject();
            mNetworkFragment = NetworkFragment.getInstance(getFragmentManager(), networkObject);
        }
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment.startDownload();
            mDownloading = true;
        }
    }

    private NetworkObject createRequestNetworkObject()
    {
        // TODO: get url
        String url = "https://api.openweathermap.org/data/2.5/forecast?id=524901";
        Map<String, String> requestProperties = new HashMap<>();
        requestProperties.put("x-api-key", getBaseContext().getString(R.string.open_weather_api_key));
        return new NetworkObject(url, HttpMethodType.GET, requestProperties);
    }
}
