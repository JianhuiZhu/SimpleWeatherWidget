package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.current.CurrentWeatherResponse;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.forecast.DetailWeatherForecastResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jianhuizhu on 2017-01-25.
 */

public interface WeatherAPI {

    @GET("/data/2.5/weather")
    Observable<CurrentWeatherResponse> getCurrentWeatherForecast(@Query("lat")double latitude, @Query("lon")double longitude, @Query("appid")String appId);

    @GET("/data/2.5/forecast")
    Observable<DetailWeatherForecastResponse> get5DayWeatherForecast(@Query("lat")double latitude, @Query("lon")double longitude, @Query("appid")String appId);


}
