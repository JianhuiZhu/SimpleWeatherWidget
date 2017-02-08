package com.jianhui_zhu.simpleweatherwidget.viewmodel;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.jianhui_zhu.simpleweatherwidget.PermissionUtil;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.SimpleWeatherReceiver;
import com.jianhui_zhu.simpleweatherwidget.Util;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.WeatherManager;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.current.CurrentWeatherResponse;


import javax.inject.Inject;

import rx.functions.Action1;

import static com.jianhui_zhu.simpleweatherwidget.Util.HUMIDITY;
import static com.jianhui_zhu.simpleweatherwidget.Util.TEMPERATURE;

/**
 * Created by jianhuizhu on 2017-01-20.
 */

public class ViewModelSimpleWeather {

    @Inject
    WeatherManager manager;
    public ViewModelSimpleWeather(WeatherManager manager){
        this.manager = manager;
    }

    public void refreshCurrentLocationWeather(Context context,Intent intent) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context,SimpleWeatherReceiver.class);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_basic);


        //update each view with given content
        String temperature = intent.getStringExtra(TEMPERATURE);
        remoteViews.setTextViewText(R.id.temperature_text_view,temperature);

        String humidity = intent.getStringExtra(HUMIDITY);
        remoteViews.setTextViewText(R.id.humidity_text_view,humidity);

        appWidgetManager.updateAppWidget(componentName,remoteViews);

    }
    public void initSetting(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds){
        Log.d(getClass().getSimpleName(),"start initSetting");
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_basic);
        remoteViews.setOnClickPendingIntent(R.id.widget, Util.startActivity(context, PermissionUtil.REQUEST_PERMISSION));
        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);

    }
}
