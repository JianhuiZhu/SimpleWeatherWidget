package com.jianhui_zhu.simpleweatherwidget;

import android.support.annotation.NonNull;
import android.widget.RemoteViews;
import android.zetterstrom.com.forecast.models.DataPoint;
import android.zetterstrom.com.forecast.models.Forecast;
import android.zetterstrom.com.forecast.models.Icon;

/**
 * Created by jianhuizhu on 2017-01-19.
 */

public final class Util {
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

    private static void weatherIconSetter(RemoteViews remoteViews, int id, @NonNull DataPoint dataPoint){
        Icon icon = dataPoint.getIcon();
        if(icon == Icon.CLEAR_DAY){
            remoteViews.setImageViewResource(id,R.drawable.ic_clear_day);
        }else if(icon == Icon.CLEAR_NIGHT){
            remoteViews.setImageViewResource(id,R.drawable.ic_clear_night);
        }else if(icon == Icon.CLOUDY){
            remoteViews.setImageViewResource(id,R.drawable.ic_cloudy);
        }else if(icon == Icon.FOG){
            remoteViews.setImageViewResource(id,R.drawable.ic_foggy);
        }else if(icon == Icon.RAIN){
            Double intensity = dataPoint.getPrecipIntensity();
            remoteViews.setImageViewResource(id,R.drawable.ic_light_rain);
        }else if(icon == Icon.PARTLY_CLOUDY_DAY){

        }
    }
}
