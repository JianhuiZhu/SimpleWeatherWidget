package com.jianhui_zhu.simpleweatherwidget.utils;

import android.content.Context;

import java.math.BigDecimal;

import static com.jianhui_zhu.simpleweatherwidget.utils.WeatherUtil.*;

/**
 * Created by jianhuizhu on 2017-02-25.
 */

public class StringFormatUtil {
    public static String weatherLocationStringBuilder(double lat, double lon){
        return lat+","+lon;
    }
    public static String airQualityLocationStringBuilder(double lat, double lon){return lat+";"+lon;}

    public static String getFormattedMaxMinTemperature(Context context, double maxTemperature, double minTemperature){
        BigDecimal max = new BigDecimal(maxTemperature);
        BigDecimal min = new BigDecimal(minTemperature);

        StringBuilder sb = new StringBuilder(20);
        sb.append(max.intValue()).append(" / ").append(min.intValue()).append(" ");
        if(isTemperatureDisplayCelsiusByDefault(context)){
            return sb.append(CELSIUS_SIGN).toString();
        }
        return sb.append(FAHRENHEIT_SIGN).toString();
    }


    public static String getTemperatureString(Context context, double fahrenheitValue) {
        if (isTemperatureDisplayCelsiusByDefault(context)) {
            BigDecimal bd = new BigDecimal(fahrenheitToCelsius(fahrenheitValue));
            return String.valueOf(bd.intValue()) + CELSIUS_SIGN;
        } else {
            BigDecimal bd = new BigDecimal(fahrenheitValue);
            return String.valueOf(bd.intValue()) + FAHRENHEIT_SIGN;
        }
    }
}
