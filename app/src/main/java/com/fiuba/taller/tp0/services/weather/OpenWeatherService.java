package com.fiuba.taller.tp0.services.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.fiuba.taller.tp0.R;
import com.fiuba.taller.tp0.networking.DownloadCallback;
import com.fiuba.taller.tp0.networking.HttpMethodType;
import com.fiuba.taller.tp0.networking.NetworkFragment;
import com.fiuba.taller.tp0.networking.NetworkObject;
import com.fiuba.taller.tp0.networking.NetworkResult;

import javax.net.ssl.HttpsURLConnection;

public class OpenWeatherService implements WeatherService, DownloadCallback<NetworkResult> {

    private static final String SERVICE_LOG_TAG = "OpenWeatherService";
    private static final String OPEN_WEATHER_MAP_API_FORMAT =
            "http://api.openweathermap.org/data/2.5/weather?q=%s";

    private NetworkFragment mNetworkFragment = null;
    private boolean mDownloading = false;

    // DownloadCallback implementation
    @Override
    public void onResponseReceived(NetworkResult result) {
        // update UI
        Log.i("Result", result.toString());
    }

    @Override
    public NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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

    @Override
    public void getWeatherData(String cityName, Activity activity) {


        if (mNetworkFragment == null) {
            NetworkObject networkObject = createRequestNetworkObject(activity);
            mNetworkFragment = NetworkFragment.getInstance(activity.getFragmentManager(), networkObject);
        }
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment.startDownload(this);
            mDownloading = true;
        }
    }

    private NetworkObject createRequestNetworkObject(Context context)
    {
        // TODO: get url
        String url = "https://api.openweathermap.org/data/2.5/forecast?id=524901";
        Map<String, String> requestProperties = new HashMap<>();
        requestProperties.put("x-api-key", context.getString(R.string.open_weather_api_key));
        return new NetworkObject(url, HttpMethodType.GET, requestProperties);
    }
}
