package com.jianhui_zhu.simpleweatherwidget.utils;


import com.jianhui_zhu.simpleweatherwidget.R;

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


    public static int getIconIdByWeatherIconCode(String weatherIconCode) throws Exception {
        switch (weatherIconCode){
            case CLEAR_DAY:
                return R.drawable.ic_clear_day;

            case CLEAR_NIGHT:
                return R.drawable.ic_clear_night;

            case RAIN:
                return R.drawable.ic_rain;
            case SNOW:
                return R.drawable.ic_snow;

            case SLEET:
                return R.drawable.ic_sleet;

            case WIND:
                return R.drawable.ic_wind;

            case FOG:
                return R.drawable.ic_fog;

            case CLOUDY:
                return R.drawable.ic_cloudy;

            case PARTLY_CLOUDY_DAY:
                return R.drawable.ic_partly_cloud_day;

            case PARTLY_CLOUDY_NIGHT:
                return R.drawable.ic_partly_cloud_night;

            default:
                throw new Exception("Weather icon unknown "+weatherIconCode);

        }
    }

}
