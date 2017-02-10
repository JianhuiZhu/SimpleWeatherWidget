package com.jianhui_zhu.simpleweatherwidget;

import android.content.Context;
import android.content.Intent;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Main;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.current.CurrentWeatherResponse;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.forecast.DetailWeatherForecastResponse;


import static com.jianhui_zhu.simpleweatherwidget.WeatherConstant.*;

/**
 * Created by jianhuizhu on 2017-02-07.
 */

public  final class BroadcastIntentHandler {
    public static final String ACTION_WIDGET_UPDATE = "action_widget_update";
    public static final String ACTION_ACTIVITY_UPDATE = "action_activity_update";
    public static final String ACTION_QUERY_BRIEF = "action_query_brief";
    public static final String ACTION_QUERY_DETAIL = "action_query_detail";
    public static final String LOCATION_PERMISSION_NEEDED = "location_permission_needed";

    private BroadcastIntentHandler(){
    }

    public static boolean isWeatherUpdateRequestByWidget(Intent intent){
        return intent.getAction() != null && intent.getAction().equals(ACTION_QUERY_BRIEF);
    }

    public static boolean isWeatherUpdateRequestByActivity(Intent intent){
        return intent.getAction() != null && intent.getAction().equals(ACTION_QUERY_DETAIL);
    }
    public static boolean isWeatherUpdateForWidget(Intent intent){
        return intent.getAction() != null && intent.getAction().equals(ACTION_WIDGET_UPDATE);
    }
    public static boolean isWeatherUpdateForActivity(Intent intent){
        return intent.getAction() != null && intent.getAction().equals(ACTION_ACTIVITY_UPDATE);
    }

    public static void broadcastBriefWeatherUpdateForWidget(Context context,CurrentWeatherResponse response){
        Intent intent = new Intent(BroadcastIntentHandler.ACTION_WIDGET_UPDATE);
        Main main = response.getMain();

        intent.putExtra(WEATHER_ICON_SERIAL_NUMBER,response.getWeather().get(0).getIcon());
        intent.putExtra(TEMPERATURE, main.getTemp());
        intent.putExtra(WEATHER_DESCRIPTION,response.getWeather().get(0).getDescription());

        context.sendBroadcast(intent);
    }

    public static void broadcastDetailWeatherUpdateForActivity(Context context,DetailWeatherForecastResponse response){
        Intent intent = new Intent(ACTION_ACTIVITY_UPDATE);

        context.sendBroadcast(intent);
    }
    public static void broadcastLocationPermissionNeededForCaller(Context context){
        Intent intent = new Intent(LOCATION_PERMISSION_NEEDED);
        context.sendBroadcast(intent);
    }
    public static void broadcastBriefWeatherUpdateRequest(Context context){
        context.sendBroadcast(new Intent(ACTION_QUERY_BRIEF));
    }
    public static void broadcastDetailWeatherUpdateRequest(Context context){
        context.sendBroadcast(new Intent(ACTION_QUERY_DETAIL));
    }
}
