package com.jianhui_zhu.simpleweatherwidget.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.daily_weather.DetailActivity;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.ACTION_REQUEST_PERMISSION;

/**
 * Created by jianhuizhu on 2017-01-19.
 */

public final class Util {

    public static final String SETTING = "SETTING";
    private static final String CELSIUS_SIGN = " °C";
    private static final String FAHRENHEIT_SIGN = " F";
    private static final int NORTH = 0;
    private static final int EAST = 90;
    private static final int SOUTH = 180;
    private static final int WEST = 270;
    private static final int MONDAY = 1;
    private static final int TUESDAY = 2;
    private static final int WEDNESDAY = 3;
    private static final int THURSDAY = 4;
    private static final int FRIDAY = 5;
    private static final int SATURDAY = 6;
    private Util() {
    }

    public static String getTemperatureString(Context context, double fahrenheitValue) {
        if (isTemperatureDisplayCelsiusByDefault(context)) {
            BigDecimal bd = new BigDecimal(fahrenheitToCelsius(fahrenheitValue));
            return String.valueOf(bd.setScale(0, RoundingMode.HALF_UP).doubleValue()) + CELSIUS_SIGN;
        } else {
            BigDecimal bd = new BigDecimal(fahrenheitValue);
            return String.valueOf(bd.setScale(0, RoundingMode.HALF_UP).doubleValue()) + FAHRENHEIT_SIGN;
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



    private static double fahrenheitToCelsius(double fahrenheitValue){
        return new BigDecimal((fahrenheitValue - 32)/1.8)
                .setScale(1,BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }


    public static String getHumidityString(Context context, double humidity){
        BigDecimal bd = new BigDecimal(humidity * 100);
        double humidityValue = bd.setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue();
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

    public static boolean isToday(long date){
        LocalDate today = new LocalDate(System.currentTimeMillis());
        LocalDate dateToCompare = new LocalDate( date* 1000L);
        Log.d("isToday",today.toString()+" "+dateToCompare.toString());
        return today.isEqual(dateToCompare);
    }

    public static boolean isTomorrow(long date){
        LocalDate today = new LocalDate(System.currentTimeMillis());
        LocalDate dateToCompare = new LocalDate(date * 1000L);
        return today.plusDays(1).isEqual(dateToCompare);
    }

    public static String getWeekDay(Context context, long date){
        StringBuilder sb = new StringBuilder(20);
        boolean isTodayOrTomorrow = false;
        if(isToday(date)){
            return sb.append(context.getString(R.string.today)).toString();


        }else if(isTomorrow(date)){
            return sb.append(context.getString(R.string.tomorrow)).toString();

        }
            int weekday = new LocalDate(date * 1000L).getDayOfWeek();
            switch (weekday){
                case MONDAY:
                    return formatWeekday(context.getString(R.string.monday),sb);
                case TUESDAY:
                    return formatWeekday(context.getString(R.string.tuesday),sb);
                case WEDNESDAY:
                    return formatWeekday(context.getString(R.string.wednesday),sb);
                case THURSDAY:
                    return formatWeekday(context.getString(R.string.thursday),sb);
                case FRIDAY:
                    return formatWeekday(context.getString(R.string.friday),sb);
                case SATURDAY:
                    return formatWeekday(context.getString(R.string.saturday),sb);
                default:
                    return formatWeekday(context.getString(R.string.sunday),sb);
        }
    }

    private static String formatWeekday(String weekday, StringBuilder sb){

        return sb.append(weekday).toString();
    }

    public static String getDateWithProperFormat(Context context, long date){
        DateTimeFormatter fmt = DateTimeFormat.forPattern(context.getString(R.string.date_format));
        return fmt.print(new LocalDate(date));
    }

    public static String getFormatedMaxMinTemperature(Context context, double maxTemperature, double minTemperature){
        BigDecimal max = new BigDecimal(maxTemperature);
        BigDecimal min = new BigDecimal(minTemperature);

        StringBuilder sb = new StringBuilder(20);
        sb.append(max.intValue()).append(" / ").append(min.intValue()).append(" ");
        if(isTemperatureDisplayCelsiusByDefault(context)){
            return sb.append(CELSIUS_SIGN).toString();
        }
        return sb.append(FAHRENHEIT_SIGN).toString();
    }

}
