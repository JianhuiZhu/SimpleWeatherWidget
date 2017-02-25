package com.jianhui_zhu.simpleweatherwidget.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.ResponseWrapper;

/**
 * Created by jianhuizhu on 2017-02-21.
 */

public class CacheUtil {
    private static final String CACHE = "CACHE";
    private static final String TIMESTAMP = "TIMESTAMP";
    private static final long NO_TIMESTAMP = 0L;
    private static final String NO_CACHE = "NO_CACHE";
    private static final long TWO_HOUR = 7200000;
    private static long getCacheTimeStamp(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(CACHE,Context.MODE_PRIVATE);
        return sharedPreferences.getLong(TIMESTAMP, NO_TIMESTAMP);
    }
    private static boolean isTwoHoursBefore(long cacheTimeStamp){

        return Math.abs(System.currentTimeMillis() - cacheTimeStamp) >= TWO_HOUR;
    }

    public static boolean isCacheExpired(Context context){
        long timestamp = getCacheTimeStamp(context);
        return timestamp == NO_TIMESTAMP || isTwoHoursBefore(timestamp);
    }

    public static void cacheWeatherForecast(Context context, ResponseWrapper response){
        SharedPreferences sharedPreferences = context.getSharedPreferences(CACHE,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String weatherJson = gson.toJson(response,ResponseWrapper.class);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CACHE,weatherJson).apply();
        editor.putLong(TIMESTAMP,System.currentTimeMillis()).apply();
    }

    public static ResponseWrapper getWeatherForecastFromCache(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(CACHE,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String cacheResponse = sharedPreferences.getString(CACHE,NO_CACHE);
        if(cacheResponse.equals(NO_CACHE)){
            return null;
        }else{
            return gson.fromJson(cacheResponse,ResponseWrapper.class);
        }
    }
}
