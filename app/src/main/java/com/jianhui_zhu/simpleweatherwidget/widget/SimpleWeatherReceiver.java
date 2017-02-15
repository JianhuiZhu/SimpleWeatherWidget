package com.jianhui_zhu.simpleweatherwidget.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jianhui_zhu.simpleweatherwidget.BroadcastIntentHandler;
import com.jianhui_zhu.simpleweatherwidget.PermissionUtil;
import com.jianhui_zhu.simpleweatherwidget.backgroundservice.WeatherBackgroundService;
import com.jianhui_zhu.simpleweatherwidget.dagger.APIModule;
import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerAPIComponent;

import javax.inject.Inject;


import static com.jianhui_zhu.simpleweatherwidget.PermissionUtil.*;
import static com.jianhui_zhu.simpleweatherwidget.Util.startActivityWithPendingIntent;
import static com.jianhui_zhu.simpleweatherwidget.BroadcastIntentHandler.*;

/**
 * Created by jianhuizhu on 2017-01-17.
 */


public class SimpleWeatherReceiver extends AppWidgetProvider {
    @Inject
    ViewModelSimpleWeather viewModel;
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        if(!isLocationPermissionGranted(context)){
            startActivityWithPendingIntent(context);
        }
        Intent intent = new Intent(context,WeatherBackgroundService.class);
        context.startService(intent);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        DaggerAPIComponent.builder().aPIModule(new APIModule()).build().inject(this);

        viewModel.initSetting(context,appWidgetManager,appWidgetIds);
        broadcastBriefWeatherUpdateRequest(context);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        DaggerAPIComponent.builder().aPIModule(new APIModule()).build().inject(this);
        if(isWeatherUpdateForWidget(intent)){
            viewModel.refreshCurrentLocationWeather(context,intent);
        }

    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(getClass().getSimpleName(),"On disabled");
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(getClass().getSimpleName(),"On deleted");
        Intent intent = new Intent(context,WeatherBackgroundService.class);
        context.stopService(intent);
    }
}
