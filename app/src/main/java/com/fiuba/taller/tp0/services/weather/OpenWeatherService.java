package com.fiuba.taller.tp0.services.weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
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

public class OpenWeatherService implements WeatherService, DownloadCallback<NetworkResult> {

    private static final String SERVICE_LOG_TAG = "OpenWeatherService";
    private static final String OPEN_WEATHER_MAP_API_FORMAT =
            "https://api.openweathermap.org/data/2.5/weather?q=%s";

    private CitiesLoader mCitiesLoader;
    private WeatherDisplayer mWeatherDisplayer;
    private NetworkFragment mNetworkFragment = null;
    private boolean mDownloading = false;

    public OpenWeatherService(Context context) {
        mCitiesLoader = new CitiesLoader();
        mCitiesLoader.loadCities(context);
    }
    @Override
    public void getWeatherData(String cityName, WeatherDisplayer displayer, Activity activity) {
        mWeatherDisplayer = displayer;
        if (mNetworkFragment == null) {
            NetworkObject networkObject = createRequestNetworkObject(cityName, activity);
            mNetworkFragment = NetworkFragment.getInstance(activity.getFragmentManager(), networkObject);
        }
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment.startDownload(this);
            mDownloading = true;
        }
    }

    @Override
    public List<String> getCities() {
        return mCitiesLoader.getCities();
    }

    @Override
    public String getCityId(String cityName) {
        return mCitiesLoader.GetCityId(cityName);
    }

    @Override
    public void onResponseReceived(NetworkResult result) {
        // update UI
        if (result.mResultValue != null) {
            WeatherData data = parseResponse(result.mResultValue);
            mWeatherDisplayer.DisplayWeatherData(data);
        } else {
            mWeatherDisplayer.DisplayException(result.mException);
        }
        Log.i(SERVICE_LOG_TAG, result.toString());
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

    private void loadCities(Context context) {
        mCitiesLoader.loadCities(context);
    }

    private NetworkObject createRequestNetworkObject(String cityName, Context context)
    {
        String url = String.format(OPEN_WEATHER_MAP_API_FORMAT, cityName);
        url = "https://api.openweathermap.org/data/2.5/forecast?id=3435910";
        Map<String, String> requestProperties = new HashMap<>();
        requestProperties.put("x-api-key", context.getString(R.string.open_weather_api_key));
        return new NetworkObject(url, HttpMethodType.GET, requestProperties);
    }

    private WeatherData parseResponse(String response) {
        //TODO: STRING TO JASON, JASON TO WEATHERDATA
        return null;
    }
}
