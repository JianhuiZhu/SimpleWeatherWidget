package com.jianhui_zhu.simpleweatherwidget.dataprovider;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.uv.UVResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jianhuizhu on 2017-02-10.
 */

public interface UVAPI {
    @GET("/v3/uvi/{latlon}/{datetime}.json")
    Observable<UVResponse> getUV(@Path("latlon") String latLon, @Path("datetime") String datetime, @Query("apikey") String apiKey);
}
