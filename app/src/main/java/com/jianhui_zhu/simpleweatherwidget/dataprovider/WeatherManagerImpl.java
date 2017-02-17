package com.jianhui_zhu.simpleweatherwidget.dataprovider;

import android.content.Context;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.Util;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Currently;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Daily;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.DarkSkyWeatherForecastResponse;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.ResponseWrapper;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by jianhuizhu on 2017-01-26.
 */

public class WeatherManagerImpl implements WeatherManager {
    private String locationStringBuilder(double lat, double lon){
        return lat+","+lon;
    }
    WeatherAPI api;

    private boolean hasCacheForecastWeather(){return !wrapper.isWeatherForecastEmpty(); }

    private boolean isDetailWeatherCacheExpired(){
        return System.currentTimeMillis() - lastUpdateTime > UPDATE_EXPIRE_TIME;
    }

    private boolean isUserTravelLongDistance(double lat, double lon){
        return Util.getDistance(previousLatitude, previousLongitude,lat,lon) > MAX_EXPIRE_DISTANCE;
    }


    private boolean isForecastCacheValid(double lat, double lon){
        return hasCacheForecastWeather() && (!isDetailWeatherCacheExpired() || !isUserTravelLongDistance(lat,lon));
    }

    //Default expire time 2 hour
    private final static long UPDATE_EXPIRE_TIME = 7200000;
    //Default expire distance 50km
    private final static double MAX_EXPIRE_DISTANCE = 50;


    private double previousLatitude;
    private double previousLongitude;
    private long lastUpdateTime = Long.MAX_VALUE;
    private ResponseWrapper wrapper = new ResponseWrapper();
    @Inject
    public WeatherManagerImpl(WeatherAPI api){
        this.api = api;
    }

    @Override
    public Observable<Currently> getCurrentWeatherByGeo(double lat, double lon, Context context) {
        if(isForecastCacheValid(lat,lon)){
            return Observable.just(wrapper.getCurrentWeatherForecast());
        }else{
            return getWeatherForecastUpdate(context.getString(R.string.darkskyapikey),lat,lon)
                    .flatMap(new Func1<DarkSkyWeatherForecastResponse, Observable<Currently>>() {
                        @Override
                        public Observable<Currently> call(DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse) {
                            return Observable.just(wrapper.getCurrentWeatherForecast());
                        }
                    });
        }
    }

    @Override
    public Observable<Daily> getDailyWeatherForecastByGeo(double lat, double lon, Context context) {
        if(isForecastCacheValid(lat,lon)){
            return Observable.just(wrapper.getDailyWeatherForecast());
        }else{
            return getWeatherForecastUpdate(context.getString(R.string.darkskyapikey),lat,lon)
                    .flatMap(new Func1<DarkSkyWeatherForecastResponse, Observable<Daily>>() {
                        @Override
                        public Observable<Daily> call(DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse) {
                            return Observable.just(wrapper.getDailyWeatherForecast());
                        }
                    });
        }
    }
    private Observable<DarkSkyWeatherForecastResponse> getWeatherForecastUpdate(String apiKey,double lat, double lon){
        previousLatitude = lat;
        previousLongitude = lon;
        String location = locationStringBuilder(lat,lon);
        return api.getDailyWeatherForecast(apiKey,location).doOnNext(new Action1<DarkSkyWeatherForecastResponse>() {
            @Override
            public void call(DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse) {
                lastUpdateTime = System.currentTimeMillis();
                wrapper.withDarkSkyDailyWeatherResponse(darkSkyWeatherForecastResponse);
            }
        });
    }
}
