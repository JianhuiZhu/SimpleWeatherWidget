package com.jianhui_zhu.simpleweatherwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.RemoteViews;
import android.zetterstrom.com.forecast.ForecastClient;
import android.zetterstrom.com.forecast.models.DataPoint;
import android.zetterstrom.com.forecast.models.Forecast;
import android.zetterstrom.com.forecast.models.Icon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jianhuizhu on 2017-01-20.
 */

public class ViewModelSimpleWeather {
    private void updateViewByLocation(Location location, final RemoteViews remoteViews, final AppWidgetManager manager, final int[]appWidgetIds){
        Log.d(getClass().getSimpleName(), location.toString());
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        ForecastClient.getInstance().getForecast(latitude, longitude, new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Forecast forecast = response.body();
                if (forecast != null) {
                    DataPoint dataPoint = forecast.getCurrently();
                    if (dataPoint != null) {
                        remoteViews.setTextViewText(R.id.temperature_text_view, dataPoint.getApparentTemperature() + " F");
                        remoteViews.setTextViewText(R.id.humidity_text_view, dataPoint.getHumidity() + " %");
                        manager.updateAppWidget(appWidgetIds, remoteViews);
                    }
                }

            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "query weather error", t);
            }
        });
    }


    public void refreshCurrentLocationWeather(Context context, final RemoteViews remoteViews, final AppWidgetManager manager, final int[] appWidgetIds) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getAllProviders();
        Location location = null;
        for (String provider : providers) {
            location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                break;
            }
        }
        if (location != null) {
            updateViewByLocation(location,remoteViews,manager,appWidgetIds);
        } else {
            Log.d(getClass().getSimpleName(), "All location is null");
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    updateViewByLocation(location,remoteViews,manager,appWidgetIds);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            }, Looper.getMainLooper());

        }

    }
}
