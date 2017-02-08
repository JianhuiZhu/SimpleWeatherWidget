package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;


import android.content.Context;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.current.CurrentWeatherResponse;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.forecast.FiveDayWeatherForecastResponse;

import rx.Observable;

/**
 * Created by jianhuizhu on 2017-01-26.
 */

public interface WeatherManager {

    Observable<CurrentWeatherResponse> getCurrentWeatherByGeo(double lat, double lon, Context context);

    Observable<FiveDayWeatherForecastResponse> getFiveDayWeatherForecastByGeo(double lat, double lon, Context context);
}
