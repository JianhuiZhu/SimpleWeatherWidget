package com.jianhui_zhu.simpleweatherwidget;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.TimeZone;

/**
 * Created by jianhuizhu on 2017-02-16.
 */

public class WeatherIconImageUtil {
    public static final String CLEAR_DAY = "clear-day";
    public static final String CLEAR_NIGHT = "clear-night";
    public static final String RAIN = "rain";
    public static final String SNOW = "snow";
    public static final String SLEET = "sleet";
    public static final String WIND = "wind";
    public static final String FOG = "fog";
    public static final String CLOUDY = "cloudy";
    public static final String PARTLY_CLOUDY_DAY = "partly-cloudy-day";
    public static final String PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";
    public static final double ZERO = 0.0;
    public static final double MINUS_THIRTY = -30.0;
    public static final double MINUS_FIFTEEN = -15.0;
    public static final double FIFTEEN = 15.0;
    public static final double THIRTY = 30.0;
    public static final int JAN = 1;
    public static final int FEB = 2;
    public static final int MAR = 3;
    public static final int APR = 4;
    public static final int MAY = 5;
    public static final int JUN = 6;
    public static final int JUL = 7;
    public static final int AUG = 8;
    public static final int SEP = 9;
    public static final int OCT = 10;
    public static final int NOV = 11;
    public static final int DEC = 12;

    public static int getSeasonBackgroundByCurrentTime(){
        DateTime time = DateTime.now();
        int month = time.getMonthOfYear();
        if(month == DEC || month <= FEB){
            return R.drawable.hockey_winter;
        }else if(month >= SEP && month <= NOV){
            return R.drawable.maple;
        }else if(month >= JUN && month <SEP){
            return R.drawable.summer;
        }else{
            return R.drawable.spring;
        }
    }

    public static int getIconIdByWeatherIconCode(String weatherIconCode) throws Exception {
        switch (weatherIconCode){
            case CLEAR_DAY:
                return R.drawable.ic_sun;

            case CLEAR_NIGHT:
                return R.drawable.ic_moon;

            case RAIN:
                return R.drawable.ic_cloud_rain_alt;
            case SNOW:
                return R.drawable.ic_cloud_snow_alt;

            case SLEET:
                return R.drawable.ic_sleet;

            case WIND:
                return R.drawable.ic_cloud_wind;

            case FOG:
                return R.drawable.ic_cloud_fog_alt;

            case CLOUDY:
                return R.drawable.ic_cloud;

            case PARTLY_CLOUDY_DAY:
                return R.drawable.ic_cloud_sun;

            case PARTLY_CLOUDY_NIGHT:
                return R.drawable.ic_cloud_moon;

            default:
                throw new Exception("Weather icon unknown "+weatherIconCode);

        }
    }

}
