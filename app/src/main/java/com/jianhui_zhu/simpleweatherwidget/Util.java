package com.jianhui_zhu.simpleweatherwidget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jianhui_zhu.simpleweatherwidget.view.DetailActivity;

import java.math.BigDecimal;

/**
 * Created by jianhuizhu on 2017-01-19.
 */

public final class Util {

    private static final String SETTING = "SETTING";
    private static final String CELSIUS_SIGN = " °C";
    private static final String FAHRENHEIT_SIGN = " F";
    private static final String DEFAULT_TEMPERATURE_UNIT_IS_CELSIUS = "DEFAULT_TEMPERATURE_UNIT_IS_CELSIUS";
    //The equator radius
    private static final double EARTH_RADIUS = 6378.137;

    private Util() {
    }

    public static String getTemperatureDisplayString(Context context, double kelvinValue) {
        if (isTemperatureDisplayCelsiusByDefault(context)) {
            return kelvinToCelsiusString(kelvinValue);
        } else {
            return kelvinToFahrenheitString(kelvinValue);
        }
    }

    private static boolean isTemperatureDisplayCelsiusByDefault(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(DEFAULT_TEMPERATURE_UNIT_IS_CELSIUS, true);
    }

    private static String kelvinToCelsiusString(double kelvinValue) {
        return String.valueOf(kelvinToCelsius(kelvinValue)) + CELSIUS_SIGN;
    }

    private static String kelvinToFahrenheitString(double kelvinValue) {
        return String.valueOf(celsiusToFahrenheit(kelvinToCelsius(kelvinValue))) + FAHRENHEIT_SIGN;
    }

    private static double kelvinToCelsius(double kelvinValue) {
        return new BigDecimal(kelvinValue - 273.15)
                .setScale(1,BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    private static double celsiusToFahrenheit(double celsiusValue) {
        return new BigDecimal(celsiusValue * 1.8 + 32)
                .setScale(1,BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }


    public static double getDistance(double latStart, double lngStart, double latEnd, double lngEnd) {
        double radLat1 = rad(latStart);
        double radLat2 = rad(latStart);
        double a = radLat1 - radLat2;
        double b = rad(lngStart) - rad(lngEnd);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static PendingIntent startActivityWithPendingIntent(@NonNull Context context, @Nullable String action) {
        Intent intent = new Intent(context, DetailActivity.class);
        if (action != null) {
            switch (action) {
                case PermissionUtil.REQUEST_PERMISSION:
                    intent.putExtra(PermissionUtil.ACTION_TAG, PermissionUtil.REQUEST_PERMISSION);
            }
        }
        return PendingIntent.getActivity(context, 0, intent, 0);
    }
}
