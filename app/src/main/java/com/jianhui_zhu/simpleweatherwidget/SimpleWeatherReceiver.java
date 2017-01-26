package com.jianhui_zhu.simpleweatherwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.zetterstrom.com.forecast.ForecastClient;
import android.zetterstrom.com.forecast.ForecastConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by jianhuizhu on 2017-01-17.
 */

public class SimpleWeatherReceiver extends AppWidgetProvider {

    ViewModelSimpleWeather viewModel = new ViewModelSimpleWeather();
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        List<String> excludeList = new ArrayList<>();
        excludeList.add("hourly");
        excludeList.add("minutely");
        ForecastConfiguration configuration =
                new ForecastConfiguration.Builder(context.getString(R.string.apikey))
                        .setDefaultExcludeList(excludeList)
                        .setCacheDirectory(context.getCacheDir())
                        .build();
        ForecastClient.create(configuration);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_basic);
        Intent intent = new Intent(context, DetailActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget,pendingIntent);
        viewModel.refreshCurrentLocationWeather(context,null,remoteViews,appWidgetManager,appWidgetIds);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}
