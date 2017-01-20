package com.jianhui_zhu.simpleweatherwidget.model;

import android.content.Context;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.model.Response.CurrentWeatherBriefResponse;
import com.jianhui_zhu.simpleweatherwidget.model.Response.CurrentWeatherFullResponse;
import com.jianhui_zhu.simpleweatherwidget.model.Response.LocationResponse;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jianhuizhu on 2017-01-19.
 */

public class WeatherManagerImpl implements WeatherManager {
    WeatherAPI api;
    public WeatherManagerImpl(WeatherAPI api){
        this.api = api;
    }
    @Override
    public Observable<CurrentWeatherBriefResponse> getCurrentWeatherBriefByGeo(final Context context, double latitude, double longitude) {
        final String apiKey = context.getString(R.string.apikey);
        return api
                .getLocationByGeo(apiKey,"45.4928064,-73.5781321")
                .flatMap(new Func1<LocationResponse, Observable<List<CurrentWeatherBriefResponse>>>() {
                    @Override
                    public Observable<List<CurrentWeatherBriefResponse>> call(LocationResponse locationResponse) {
                        return api.getWeatherForecastBrief(locationResponse.getKey(),apiKey,false);
                    }
                }).flatMap(new Func1<List<CurrentWeatherBriefResponse>, Observable<CurrentWeatherBriefResponse>>() {
                    @Override
                    public Observable<CurrentWeatherBriefResponse> call(List<CurrentWeatherBriefResponse> currentWeatherBriefResponses) {
                        return Observable.just(currentWeatherBriefResponses.get(0));
                    }
                }).onErrorReturn(new Func1<Throwable, CurrentWeatherBriefResponse>() {
                    @Override
                    public CurrentWeatherBriefResponse call(Throwable throwable) {
                        throwable.printStackTrace();
                        return null;
                    }
                });
    }

    @Override
    public Observable<List<CurrentWeatherFullResponse>> getCurrentWeatherFullByGeo(final Context context, double latitude, double longitude) {
        return null;
    }
}
