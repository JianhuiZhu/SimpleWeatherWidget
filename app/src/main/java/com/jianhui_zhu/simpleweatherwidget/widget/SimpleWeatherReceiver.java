package com.jianhui_zhu.simpleweatherwidget.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerViewModelComponent;
import com.jianhui_zhu.simpleweatherwidget.dagger.ViewModelModule;
import com.jianhui_zhu.simpleweatherwidget.daily_weather.DailyWeatherActivity;

import javax.inject.Inject;


import static com.jianhui_zhu.simpleweatherwidget.utils.ActivityFragmentUtil.startPendingActivity;
import static com.jianhui_zhu.simpleweatherwidget.utils.PermissionUtil.*;
import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.*;

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
            startPendingActivity(context, DailyWeatherActivity.class);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        DaggerViewModelComponent.builder().viewModelModule(new ViewModelModule()).build().inject(this);

        viewModel.initSetting(context,appWidgetManager,appWidgetIds);
        startServiceForBriefWeatherUpdateRequest(context);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        DaggerViewModelComponent.builder().viewModelModule(new ViewModelModule()).build().inject(this);
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
    }
}
