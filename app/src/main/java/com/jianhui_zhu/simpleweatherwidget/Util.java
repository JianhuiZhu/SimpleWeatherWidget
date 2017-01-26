package com.jianhui_zhu.simpleweatherwidget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.zetterstrom.com.forecast.models.DataPoint;
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

    public static void weatherIconSetter(RemoteViews remoteViews, int id, ImageView imageView, @NonNull DataPoint dataPoint){
        Icon icon = dataPoint.getIcon();
        if(icon == Icon.CLEAR_DAY){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_clear_day);
            }else{
                imageView.setImageResource(R.drawable.ic_clear_day);
            }
        }else if(icon == Icon.CLEAR_NIGHT){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_clear_night);
            }else{
                imageView.setImageResource(R.drawable.ic_clear_night);
            }
        }else if(icon == Icon.CLOUDY){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_cloudy);
            }else{
                imageView.setImageResource(R.drawable.ic_cloudy);
            }
        }else if(icon == Icon.FOG){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_foggy);
            }else{
                imageView.setImageResource(R.drawable.ic_foggy);
            }
        }else if(icon == Icon.RAIN){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_light_rain);
            }else{
                imageView.setImageResource(R.drawable.ic_light_rain);
            }
        }else if(icon == Icon.PARTLY_CLOUDY_DAY){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_partly_cloudy_day);
            }else{
                imageView.setImageResource(R.drawable.ic_partly_cloudy_day);
            }
        }else if(icon == Icon.PARTLY_CLOUDY_NIGHT){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_partly_cloudy_night);
            }else{
                imageView.setImageResource(R.drawable.ic_partly_cloudy_night);
            }
        }else if(icon == Icon.HAIL){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_hail);
            }else {
                imageView.setImageResource(R.drawable.ic_hail);
            }
        }else if(icon == Icon.SLEET){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_hail);
            }else {
                imageView.setImageResource(R.drawable.ic_hail);
            }
        }else if(icon == Icon.SNOW){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_snow);
            }else{
                imageView.setImageResource(R.drawable.ic_snow);
            }
        }else if(icon == Icon.THUNDERSTORM){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_thunder);
            }else{
                imageView.setImageResource(R.drawable.ic_thunder);
            }
        }else if(icon == Icon.WIND){
            if(imageView == null) {
                remoteViews.setImageViewResource(id, R.drawable.ic_wind);
            }else{
                imageView.setImageResource(R.drawable.ic_wind);
            }
        }
    }
}
