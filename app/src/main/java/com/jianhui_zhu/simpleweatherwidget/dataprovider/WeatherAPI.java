package com.jianhui_zhu.simpleweatherwidget.dataprovider;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.DarkSkyWeatherForecastResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jianhuizhu on 2017-01-25.
 */

public interface WeatherAPI {
    @GET("/forecast/{dark_api_id}/{location}?exclude=[minutely,hourly,flags]")
    Observable<DarkSkyWeatherForecastResponse> getDailyWeatherForecast(@Path("dark_api_id") String apiId, @Path("location") String locationString);
}
