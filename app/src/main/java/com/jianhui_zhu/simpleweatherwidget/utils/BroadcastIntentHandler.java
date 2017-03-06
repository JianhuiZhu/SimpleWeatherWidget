package com.jianhui_zhu.simpleweatherwidget.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jianhui_zhu.simpleweatherwidget.background_service.WeatherDetailService;
import com.jianhui_zhu.simpleweatherwidget.background_service.WidgetService;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AirQualityData;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Daily;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.DailyDataPoint;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.CurrentDataWrapper;


import static com.jianhui_zhu.simpleweatherwidget.utils.Constant.*;

/**
 * Created by jianhuizhu on 2017-02-07.
 */

public final class BroadcastIntentHandler {
    public static final String ACTION_WIDGET_UPDATE = "action_widget_update";
    public static final String ACTION_ACTIVITY_UPDATE = "action_activity_update";
    public static final String ACTION_QUERY_BRIEF = "action_query_brief";
    public static final String ACTION_QUERY_DETAIL = "action_query_detail";
    public static final String ACTION_REQUEST_PERMISSION = "action_request_permission";
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

    public static void broadcastBriefWeatherUpdateForWidget(Context context,CurrentDataWrapper currently){
        Intent intent = new Intent(BroadcastIntentHandler.ACTION_WIDGET_UPDATE);

        intent.putExtra(WEATHER_ICON_SERIAL_NUMBER,currently.getCurrently().getIcon());
        intent.putExtra(TEMPERATURE, currently.getCurrently().getTemperature());
        intent.putExtra(WEATHER_DESCRIPTION,currently.getCurrently().getSummary());
        intent.putExtra(AIR_QUALITY,currently.getAirQualityData());

        context.sendBroadcast(intent);
    }

    public static void broadcastDetailWeatherUpdateForActivity(Context context, Daily daily, AirQualityData airQualityData){
        Intent intent = new Intent(ACTION_ACTIVITY_UPDATE);
        Log.d(BroadcastIntentHandler.class.getSimpleName(),"start check daily data");

        for(int index = 0; index<daily.getData().size(); index++){
            DailyDataPoint dataPoint = daily.getData().get(index);
            if(DateTimeUtil.isToday(dataPoint.getTime())){
                intent.putExtra(TODAY_WEATHER,dataPoint);
                Log.d(BroadcastIntentHandler.class.getSimpleName(),"has today's data");
                daily.getData().remove(index);
                break;
            }
        }
        intent.putExtra(WEATHER_LIST,daily);
        intent.putExtra(AIR_QUALITY,airQualityData);


        Log.d("Daily data",daily.toString());

        context.sendBroadcast(intent);
    }
    public static void broadcastLocationPermissionNeededForCaller(Context context){
        Intent intent = new Intent(LOCATION_PERMISSION_NEEDED);
        context.sendBroadcast(intent);
    }
    public static void startServiceForBriefWeatherUpdateRequest(Context context){
        Intent intent = new Intent(context,WidgetService.class);
        context.startService(intent);
    }
    public static void startServiceForDetailWeatherUpdateRequest(Context context){
        Intent intent = new Intent(context,WeatherDetailService.class);
        context.startService(intent);
    }
}
