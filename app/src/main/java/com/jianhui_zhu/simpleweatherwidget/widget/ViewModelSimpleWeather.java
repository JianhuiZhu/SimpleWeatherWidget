package com.jianhui_zhu.simpleweatherwidget.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.utils.WeatherUtil;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AirQualityData;


import static com.jianhui_zhu.simpleweatherwidget.utils.AirQualityUtil.getAirQualityBackgroundColorByAQI;
import static com.jianhui_zhu.simpleweatherwidget.utils.AirQualityUtil.getAirQualityDescriptionByAQI;
import static com.jianhui_zhu.simpleweatherwidget.utils.WeatherConstant.*;
import static com.jianhui_zhu.simpleweatherwidget.utils.WeatherUtil.getTemperatureString;
import static com.jianhui_zhu.simpleweatherwidget.utils.WeatherIconImageUtil.*;
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
        AirQualityData airQualityData = intent.getParcelableExtra(AIR_QUALITY);
        int aqi = airQualityData.getAqi();

        try {
            remoteViews.setImageViewResource(R.id.weather_icon_image_view,getIconIdByWeatherIconCode(icon));
        } catch (Exception e) {
            e.printStackTrace();
        }
        remoteViews.setTextViewText(R.id.air_condition_text_view, getAirQualityDescriptionByAQI(context,aqi));
        remoteViews.setTextViewText(R.id.temperature_text_view, getTemperatureString(context,temperature));

        remoteViews.setInt(R.id.air_quality_area,"setBackgroundColor",getAirQualityBackgroundColorByAQI(context,aqi));

        appWidgetManager.updateAppWidget(componentName,remoteViews);

    }
    public void initSetting(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds){
        Log.d(getClass().getSimpleName(),"start initSetting");
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_basic);

        remoteViews.setOnClickPendingIntent(R.id.widget, WeatherUtil.startActivityWithPendingIntent(context));
        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);

    }
}
