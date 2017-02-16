package com.jianhui_zhu.simpleweatherwidget.dataprovider;


import android.content.Context;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.DarkSkyCurrentWeatherResponse;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.DarkSkyDailyWeatherResponse;

import rx.Observable;

/**
 * Created by jianhuizhu on 2017-01-26.
 */

public interface WeatherManager {

    Observable<DarkSkyCurrentWeatherResponse> getCurrentWeatherByGeo(double lat, double lon, Context context);

    Observable<DarkSkyDailyWeatherResponse> getDailyWeatherForecastByGeo(double lat, double lon, Context context);


}
