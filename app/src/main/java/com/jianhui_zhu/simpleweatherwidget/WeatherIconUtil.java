package com.jianhui_zhu.simpleweatherwidget;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.TimeZone;

/**
 * Created by jianhuizhu on 2017-02-16.
 */

public class WeatherIconUtil {
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


    private static boolean iscurrentTimeDuringDayLightTime(int unixSunriseTime, int unixSunsetTime){
        DateTime sunriseTime = new DateTime(unixSunriseTime * 1000L).withZone(DateTimeZone.forTimeZone(TimeZone.getDefault()));
        DateTime sunsetTime = new DateTime(unixSunsetTime * 1000L).withZone(DateTimeZone.forTimeZone(TimeZone.getDefault()));
        DateTime now = DateTime.now(DateTimeZone.forTimeZone(TimeZone.getDefault()));
        return now.isAfter(sunriseTime) && now.isBefore(sunsetTime);
    }
    public static int getIconIdByWeatherIconCode(String weatherIconCode, int unixSunriseTime, int unixSunsetTime) throws Exception {
        switch (weatherIconCode){
            case CLEAR_DAY:
                return R.drawable.ic_sun;

            case CLEAR_NIGHT:
                return R.drawable.ic_moon;

            case RAIN:
                return iscurrentTimeDuringDayLightTime(unixSunriseTime, unixSunsetTime)
                        ? R.drawable.ic_cloud_rain_sun_alt : R.drawable.ic_cloud_rain_moon_alt;
            case SNOW:
                return iscurrentTimeDuringDayLightTime(unixSunriseTime, unixSunsetTime)
                        ? R.drawable.ic_cloud_snow_sun_alt : R.drawable.ic_cloud_snow_moon_alt;

            case SLEET:
                return R.drawable.ic_cloud_snow_alt;

            case WIND:
                return R.drawable.ic_cloud_wind;

            case FOG:
                return iscurrentTimeDuringDayLightTime(unixSunriseTime, unixSunsetTime)
                        ? R.drawable.ic_cloud_fog_sun_alt : R.drawable.ic_cloud_fog_moon_alt;

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

    public static int getThermometerIconByCelsiusDegree(double celsiusDegree){
        if(celsiusDegree >= THIRTY){
            return R.drawable.ic_thermometer_100;
        }else if(celsiusDegree >= FIFTEEN){
            return R.drawable.ic_thermometer_75;
        }else if(celsiusDegree >= ZERO){
            return R.drawable.ic_thermometer_50;
        }else if(celsiusDegree >= MINUS_FIFTEEN){
            return R.drawable.ic_thermometer_25;
        }else{
            return R.drawable.ic_thermometer_zero;
        }
    }

}
