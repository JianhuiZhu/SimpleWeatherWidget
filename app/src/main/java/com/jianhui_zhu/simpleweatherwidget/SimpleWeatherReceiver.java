package com.jianhui_zhu.simpleweatherwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.jianhui_zhu.simpleweatherwidget.dagger.APIModule;
import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerAPIComponent;
import com.jianhui_zhu.simpleweatherwidget.model.WeatherManager;

import javax.inject.Inject;

/**
 * Created by jianhuizhu on 2017-01-17.
 */

public class SimpleWeatherReceiver extends AppWidgetProvider {
    @Inject
    WeatherManager manager;
    @Inject
    ViewModelSimpleWeather viewModel;
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        DaggerAPIComponent.builder().aPIModule(new APIModule()).build().inject(this);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        viewModel.refreshCurrentLocationWeather(manager,context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}
