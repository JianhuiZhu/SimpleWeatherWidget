package com.jianhui_zhu.simpleweatherwidget.model;

import com.jianhui_zhu.simpleweatherwidget.model.Response.CurrentWeatherBriefResponse;
import com.jianhui_zhu.simpleweatherwidget.model.Response.LocationResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jianhuizhu on 2017-01-17.
 */

public interface WeatherAPI {
    @GET("/locations/v1/cities/geoposition/search")
    Observable<LocationResponse> getLocationByGeo(
            @Query("apikey") String APIKey,
            @Query("q") String query);


    @GET("/forecasts/v1/hourly/1hour/{locationKey}")
    Observable<List<CurrentWeatherBriefResponse>> getWeatherForecastBrief(
            @Path("locationKey") String locationKey,
            @Query("apikey") String APIKey,
            @Query("details") boolean details);


}
