package com.jianhui_zhu.simpleweatherwidget.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.jianhui_zhu.simpleweatherwidget.BuildConfig;
import com.jianhui_zhu.simpleweatherwidget.PermissionUtil;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.Util;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherManager;


import javax.inject.Inject;

import static com.jianhui_zhu.simpleweatherwidget.WeatherConstant.*;
import static com.jianhui_zhu.simpleweatherwidget.Util.getTemperatureString;

/**
 * Created by jianhuizhu on 2017-01-20.
 */

public class ViewModelSimpleWeather {


    public void refreshCurrentLocationWeather(Context context,Intent intent) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context,SimpleWeatherReceiver.class);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_basic);


        //update each view with given content
        double temperature = intent.getDoubleExtra(TEMPERATURE,0.0);
        String icon = intent.getStringExtra(WEATHER_ICON_SERIAL_NUMBER);
//        remoteViews.setImageViewUri(R.id.weather_icon_image_view, Uri.parse(BuildConfig.WEATHER_ICON_BASE_URL+icon+".png"));
        remoteViews.setTextViewText(R.id.temperature_text_view, getTemperatureString(context,temperature));

        appWidgetManager.updateAppWidget(componentName,remoteViews);

    }
    public void initSetting(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds){
        Log.d(getClass().getSimpleName(),"start initSetting");
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_basic);

        remoteViews.setOnClickPendingIntent(R.id.widget, Util.startActivityWithPendingIntent(context));
        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);

    }
}
