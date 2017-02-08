package com.jianhui_zhu.simpleweatherwidget;

import android.*;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.zetterstrom.com.forecast.models.DataPoint;
import android.zetterstrom.com.forecast.models.Icon;

import rx.Observable;

/**
 * Created by jianhuizhu on 2017-01-19.
 */

public final class Util {
    public static final String MAX_TEMPERATURE = "MAX_TEMPERATURE";
    public static final String MIN_TEMPERATURE = "MIN_TEMPERATURE";
    public static final String HUMIDITY = "HUMIDITY";
    public static final String TEMPERATURE = "TEMPERATURE";
    //The equator radius
    private static final double EARTH_RADIUS = 6378.137;
    private Util(){}
    public static double fahrenheitToCelsius(double fahrenheit){
        return (fahrenheit - 32) / 1.8;
    }
    public static String fahrenheitToCelsiusString(double fahrenheit){
        return String.valueOf(fahrenheitToCelsius(fahrenheit))+" °C";
    }

    public static String celsiusToFahrenheitString(double celsius){
        return String.valueOf(celsiusToFahrenheit(celsius))+ " F";
    }

    public static double celsiusToFahrenheit(double celsius){
        return celsius * 1.8 + 32;
    }


    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    public static double GetDistance(double latStart, double lngStart, double latEnd, double lngEnd)
    {
        double radLat1 = rad(latStart);
        double radLat2 = rad(latStart);
        double a = radLat1 - radLat2;
        double b = rad(lngStart) - rad(lngEnd);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }


    public static PendingIntent startActivity(@NonNull Context context, @Nullable String action){
        Intent intent = new Intent(context, DetailActivity.class);
        if(action != null){
            switch (action){
                case PermissionUtil.REQUEST_PERMISSION:
                    intent.putExtra(PermissionUtil.ACTION_TAG,PermissionUtil.REQUEST_PERMISSION);
            }
        }
        return PendingIntent.getActivity(context, 0, intent, 0);

    }


}
