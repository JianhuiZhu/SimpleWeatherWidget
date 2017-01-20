package com.jianhui_zhu.simpleweatherwidget;

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
}
