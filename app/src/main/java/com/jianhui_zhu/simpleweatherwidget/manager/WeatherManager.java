package com.jianhui_zhu.simpleweatherwidget.manager;


import android.content.Context;

import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AirQualityData;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Daily;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.CurrentDataWrapper;

import rx.Observable;

/**
 * Created by jianhuizhu on 2017-01-26.
 */

public interface WeatherManager {

    Observable<CurrentDataWrapper> getCurrentWeatherByGeo(double lat, double lon, Context context);

    Observable<Daily> getDailyWeatherForecastByGeo(double lat, double lon, Context context);

    Observable<AirQualityData> getAirQualityByGeo(double lat,double lon,Context context);
}
