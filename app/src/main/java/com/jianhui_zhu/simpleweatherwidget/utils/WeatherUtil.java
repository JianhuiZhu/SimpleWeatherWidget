package com.jianhui_zhu.simpleweatherwidget.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.daily_weather.DetailActivity;

import java.math.BigDecimal;

import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.ACTION_REQUEST_PERMISSION;

/**
 * Created by jianhuizhu on 2017-01-19.
 */

public final class WeatherUtil {

    public static final String SETTING = "SETTING";
    public static final String CELSIUS_SIGN = " °C";
    public static final String FAHRENHEIT_SIGN = " F";
    private static final int NORTH = 0;
    private static final int EAST = 90;
    private static final int SOUTH = 180;
    private static final int WEST = 270;

    private WeatherUtil() {
    }

    public static boolean isTemperatureDisplayCelsiusByDefault(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sharedPreferences.getString(
                context.getString(R.string.temperature_unit_setting),
                context.getString(R.string.celsius))
                .equals(context.getString(R.string.celsius));
    }

    public static boolean isWindSpeedDisplayMperSByDefault(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTING,Context.MODE_PRIVATE);
        return sharedPreferences.getString(
                context.getString(R.string.wind_speed_unit_setting),
                context.getString(R.string.meter_second))
                .equals(context.getString(R.string.meter_second));
    }



    public static double fahrenheitToCelsius(double fahrenheitValue){
        return new BigDecimal((fahrenheitValue - 32)/1.8)
                .setScale(1,BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }


    public static String getHumidityString(Context context, double humidity){
        BigDecimal bd = new BigDecimal(humidity * 100);
        int humidityValue = bd.intValue();
        return String.valueOf(humidityValue) + context.getString(R.string.percentage_sign);
    }


    public static PendingIntent startActivityWithPendingIntent(@NonNull Context context) {
        Intent intent = new Intent(context, DetailActivity.class);

        if(!PermissionUtil.isLocationPermissionGranted(context)){
            intent.setAction(ACTION_REQUEST_PERMISSION);
        }

        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    public static String getWindSpeedString(Context context, double windSpeed){
        if(isWindSpeedDisplayMperSByDefault(context)){
            BigDecimal windSpeedValue = new BigDecimal(windSpeed);
            return String.valueOf(
                    windSpeedValue.setScale(1,BigDecimal.ROUND_HALF_UP))
                    +" "+
                    context.getString(R.string.meter_per_second);
        }else{
            BigDecimal windSpeedValue = new BigDecimal(windSpeed * 2.23694);
            return String.valueOf(windSpeedValue.setScale(1,BigDecimal.ROUND_HALF_UP))
                    +" "+
                    context.getString(R.string.mile_per_hour);
        }
    }

    public static String getWindDirectionString(Context context, int degree){
        StringBuilder sb = new StringBuilder();
        if(degree == NORTH){
            sb.append(context.getString(R.string.north));
        }else if(degree == EAST){
            sb.append(context.getString(R.string.east));
        }else if(degree == SOUTH){
            sb.append(context.getString(R.string.south));
        }else if(degree == WEST){
            sb.append(context.getString(R.string.west));
        }else if(degree > NORTH && degree < EAST){
            sb.append(context.getString(R.string.north))
                    .append(" ")
                    .append(context.getString(R.string.east));
        }else if(degree >EAST && degree < SOUTH){
            sb.append(context.getString(R.string.south))
                    .append(" ")
                    .append(context.getString(R.string.east));
        }else if(degree > SOUTH && degree < WEST){
            sb.append(context.getString(R.string.south))
                    .append(" ")
                    .append(context.getString(R.string.west));
        }else{
            sb.append(context.getString(R.string.north))
                    .append(" ")
                    .append(context.getString(R.string.west));
        }

        return sb.append(" ")
                .append(degree)
                .append("°").toString();
    }


}
