package com.jianhui_zhu.simpleweatherwidget.data_provider;

import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.AirQualityResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jianhuizhu on 2017-02-10.
 */

public interface AirQualityAPI {
    @GET("/feed/geo:{latlon}/")
    Observable<AirQualityResponse> getAirQualityByGeo(@Path("latlon")String locationString, @Query("token") String apiKey);
}
