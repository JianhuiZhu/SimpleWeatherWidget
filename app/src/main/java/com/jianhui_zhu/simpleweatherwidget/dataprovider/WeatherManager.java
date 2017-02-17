package com.jianhui_zhu.simpleweatherwidget.dataprovider;


import android.content.Context;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Currently;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Daily;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.CurrentDataWrapper;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.DarkSkyWeatherForecastResponse;

import rx.Observable;

/**
 * Created by jianhuizhu on 2017-01-26.
 */

public interface WeatherManager {

    Observable<CurrentDataWrapper> getCurrentWeatherByGeo(double lat, double lon, Context context);

    Observable<Daily> getDailyWeatherForecastByGeo(double lat, double lon, Context context);


}
