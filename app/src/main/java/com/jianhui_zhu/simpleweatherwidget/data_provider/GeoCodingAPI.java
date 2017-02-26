package com.jianhui_zhu.simpleweatherwidget.data_provider;

import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.GeoCodingResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jianhuizhu on 2017-02-25.
 */

public interface GeoCodingAPI {
    @GET("/maps/api/geocode/json")
    Observable<GeoCodingResponse>getLocationInformationByLatLon(@Query("latlng") String latlng, @Query("key") String googleAPIKey);
}
