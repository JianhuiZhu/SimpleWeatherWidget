package com.jianhui_zhu.simpleweatherwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.zetterstrom.com.forecast.ForecastClient;
import android.zetterstrom.com.forecast.ForecastConfiguration;

import retrofit2.Call;

/**
 * Created by jianhuizhu on 2017-01-17.
 */

public class SimpleWeatherReceiver extends AppWidgetProvider {
    ViewModelSimpleWeather viewModel = new ViewModelSimpleWeather();
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        ForecastConfiguration configuration =
                new ForecastConfiguration.Builder(context.getString(R.string.apikey))
                        .setCacheDirectory(context.getCacheDir())
                        .build();
        ForecastClient.create(configuration);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_basic);
        viewModel.refreshCurrentLocationWeather(context,remoteViews,appWidgetManager,appWidgetIds);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}
