package com.jianhui_zhu.simpleweatherwidget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Wind;
import com.jianhui_zhu.simpleweatherwidget.detailweather.DetailActivity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.jianhui_zhu.simpleweatherwidget.BroadcastIntentHandler.ACTION_REQUEST_PERMISSION;

/**
 * Created by jianhuizhu on 2017-01-19.
 */

public final class Util {

    private static final String SETTING = "SETTING";
    private static final String CELSIUS_SIGN = " °C";
    private static final String FAHRENHEIT_SIGN = " F";
    //The equator radius
    private static final double EARTH_RADIUS = 6378.137;
    private static final int NORTH = 0;
    private static final int EAST = 90;
    private static final int SOUTH = 180;
    private static final int WEST = 270;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    private Util() {
    }

    public static String getTemperatureString(Context context, double kelvinValue) {
        if (isTemperatureDisplayCelsiusByDefault(context)) {
            return kelvinToCelsiusString(kelvinValue);
        } else {
            return kelvinToFahrenheitString(kelvinValue);
        }
    }

    private static boolean isTemperatureDisplayCelsiusByDefault(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sharedPreferences.getString(
                context.getString(R.string.temperature_unit_setting),
                context.getString(R.string.celsius))
                .equals(context.getString(R.string.celsius));
    }

    private static boolean isWindSpeedDisplayMperSByDefault(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTING,Context.MODE_PRIVATE);
        return sharedPreferences.getString(
                context.getString(R.string.wind_speed_unit_setting),
                context.getString(R.string.meter_second))
                .equals(context.getString(R.string.meter_second));
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


    public static String getHumidityString(Context context, int humidity){
        return String.valueOf(humidity) + context.getString(R.string.percentage_sign);
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

    public static PendingIntent startActivityWithPendingIntent(@NonNull Context context) {
        Intent intent = new Intent(context, DetailActivity.class);

        if(!PermissionUtil.isLocationPermissionGranted(context)){
            intent.setAction(ACTION_REQUEST_PERMISSION);
        }

        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    public static String getWindSpeedString(Context context, Wind wind){
        if(isWindSpeedDisplayMperSByDefault(context)){
            BigDecimal windSpeed = new BigDecimal(wind.getSpeed());
            return String.valueOf(
                    windSpeed.setScale(2,BigDecimal.ROUND_HALF_UP))
                    +" "+
                    context.getString(R.string.meter_per_second);
        }else{
            BigDecimal windSpeed = new BigDecimal(wind.getSpeed() * 2.23694);
            return String.valueOf(windSpeed.setScale(2,BigDecimal.ROUND_HALF_UP))
                    +" "+
                    context.getString(R.string.mile_per_hour);
        }
    }

    public static String getWindDirectionString(Context context, Wind wind){
        StringBuilder sb = new StringBuilder();
        BigDecimal bd = new BigDecimal(wind.getDeg());
        int degree = bd.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
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

    private static boolean isToday(DateTime date){
        DateTime today = DateTime.now().withZone(DateTimeZone.forTimeZone(TimeZone.getDefault())).withTimeAtStartOfDay();
        return today.equals(date.withZone(DateTimeZone.forTimeZone(TimeZone.getDefault())).withTimeAtStartOfDay());
    }

    private static boolean isTomorrow(DateTime date){
        DateTime tomorrow = DateTime.now().withZone(DateTimeZone.forTimeZone(TimeZone.getDefault())).plusDays(1).withTimeAtStartOfDay();
        return tomorrow.equals(date.withZone(DateTimeZone.forTimeZone(TimeZone.getDefault())).withTimeAtStartOfDay());
    }

    public static String getWeekDay(Context context, DateTime date){
        StringBuilder sb = new StringBuilder(20);
        boolean isTodayOrTomorrow = false;
        if(isToday(date)){
            isTodayOrTomorrow = true;
            sb.append(context.getString(R.string.today));

        }else if(isTomorrow(date)){
            isTodayOrTomorrow = true;
            sb.append(context.getString(R.string.tomorrow));

        }
            int weekday = date.withZone(DateTimeZone.forTimeZone(TimeZone.getDefault())).getDayOfWeek();
            switch (weekday){
                case MONDAY:
                    return formatWeekday(isTodayOrTomorrow,context.getString(R.string.monday),sb);
                case TUESDAY:
                    return formatWeekday(isTodayOrTomorrow,context.getString(R.string.tuesday),sb);
                case WEDNESDAY:
                    return formatWeekday(isTodayOrTomorrow,context.getString(R.string.wednesday),sb);
                case THURSDAY:
                    return formatWeekday(isTodayOrTomorrow,context.getString(R.string.thursday),sb);
                case FRIDAY:
                    return formatWeekday(isTodayOrTomorrow,context.getString(R.string.friday),sb);
                case SATURDAY:
                    return formatWeekday(isTodayOrTomorrow,context.getString(R.string.saturday),sb);
                default:
                    return formatWeekday(isTodayOrTomorrow,context.getString(R.string.sunday),sb);
        }
    }

    private static String formatWeekday(boolean isTodayOrTomorrow, String weekday, StringBuilder sb){
        if(isTodayOrTomorrow){
            return sb.append("(").append(weekday).append(")").toString();
        }
        return sb.append(weekday).toString();
    }

    public static String getDateWithProperFormat(Context context, DateTime date){
        DateTimeFormatter fmt = DateTimeFormat.forPattern(context.getString(R.string.date_format));
        return fmt.print(date.withZone(DateTimeZone.forTimeZone(TimeZone.getDefault())).toDateTime());
    }
}
