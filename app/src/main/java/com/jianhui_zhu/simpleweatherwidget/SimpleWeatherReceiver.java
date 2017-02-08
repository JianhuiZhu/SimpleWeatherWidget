package com.jianhui_zhu.simpleweatherwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jianhui_zhu.simpleweatherwidget.dagger.APIModule;
import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerAPIComponent;
import com.jianhui_zhu.simpleweatherwidget.viewmodel.ViewModelSimpleWeather;

import javax.inject.Inject;

import static com.jianhui_zhu.simpleweatherwidget.PermissionUtil.*;
import static com.jianhui_zhu.simpleweatherwidget.Util.startActivity;

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
            startActivity(context,PermissionUtil.REQUEST_PERMISSION);
        }

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        DaggerAPIComponent.builder().aPIModule(new APIModule()).build().inject(this);
        if(viewModel == null){
            Log.d(getClass().getSimpleName(),"viewmodel is null");
        }
        viewModel.initSetting(context,appWidgetManager,appWidgetIds);
        Intent intent = new Intent(context,WeatherService.class);
        intent.setPackage(context.getPackageName());
        intent.setAction(Constant.ACTION_QUERY_CURRENT);
        context.startService(intent);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        DaggerAPIComponent.builder().aPIModule(new APIModule()).build().inject(this);
        if(intent.getAction().equals(Constant.ACTION_UPDATE)){
            Log.d(getClass().getSimpleName(),"receive query weather feedback");
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
