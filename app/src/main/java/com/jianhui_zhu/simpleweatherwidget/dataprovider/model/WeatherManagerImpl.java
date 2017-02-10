package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import android.content.Context;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.Util;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.current.CurrentWeatherResponse;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.forecast.DetailWeatherForecastResponse;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by jianhuizhu on 2017-01-26.
 */

public class WeatherManagerImpl implements WeatherManager {
    WeatherAPI api;
    private boolean hasCacheCurrentWeather(){
        return currentWeather != null;
    }
    private boolean isCurrentWeatherCacheExpired(long currentTime){
        return currentTime - lastCurrentWeatherUpdate > UPDATE_EXPIRE_TIME;
    }
    private boolean isDetailWeatherCacheExpired(long currentTime){
        return currentTime - lastFiveDayWeatherUpdate > UPDATE_EXPIRE_TIME;
    }

    private boolean isUserTravelLongDistance(double lat, double lon){
        return Util.getDistance(previousLatitude, previousLongitude,lat,lon) > MAX_EXPIRE_DISTANCE;
    }
    private boolean isCacheValid(long currentTime, double lat, double lon){
        return hasCacheCurrentWeather() && (!isCurrentWeatherCacheExpired(currentTime) || !isUserTravelLongDistance(lat,lon));
    }


    private final static String CURRENT_WEATHER_QUERY_ERROR = "CURRENT_WEATHER_ERROR";
    private final static String FIVE_DAY_WEATHER_QUERY_ERROR = "FIVE_DAY_WEATHER_ERROR";
    //Default expire time 2 hour
    private final static long UPDATE_EXPIRE_TIME = 7200000;
    //Default expire distance 50km
    private final static double MAX_EXPIRE_DISTANCE = 50;


    private double previousLatitude;
    private double previousLongitude;
    private long lastCurrentWeatherUpdate = Long.MAX_VALUE;
    private long lastFiveDayWeatherUpdate = Long.MAX_VALUE;
    private CurrentWeatherResponse currentWeather;

    private DetailWeatherForecastResponse fiveDayWeather;
    @Inject
    public WeatherManagerImpl(WeatherAPI api){
        this.api = api;
    }

    @Override
    public Observable<CurrentWeatherResponse> getCurrentWeatherByGeo(double lat, double lon, Context context) {
        long currentTime = System.currentTimeMillis();
        if(isCacheValid(currentTime,lat,lon)){
            return Observable.just(this.currentWeather);
        }else{
            lastCurrentWeatherUpdate = currentTime;
            return api.getCurrentWeatherForecast(lat,lon,context.getString(R.string.apikey))
                    .doOnNext(new Action1<CurrentWeatherResponse>() {
                        @Override
                        public void call(CurrentWeatherResponse currentWeatherResponse) {
                            currentWeather = currentWeatherResponse;
                        }
                    });
        }
    }

    @Override
    public Observable<DetailWeatherForecastResponse> getFiveDayWeatherForecastByGeo(double lat, double lon, Context context) {
        final long currentTime = System.currentTimeMillis();
        if(isCacheValid(currentTime,lat,lon)){
            previousLatitude = lat;
            previousLongitude = lon;
            return Observable.just(this.fiveDayWeather);
        }else{
            previousLatitude = lat;
            previousLongitude = lon;
            lastFiveDayWeatherUpdate = currentTime;
            return api.get5DayWeatherForecast(lat,lon,context.getString(R.string.apikey))
                    .doOnNext(new Action1<DetailWeatherForecastResponse>() {
                        @Override
                        public void call(DetailWeatherForecastResponse detailWeatherForecastResponse) {
                            lastFiveDayWeatherUpdate = currentTime;
                            fiveDayWeather = detailWeatherForecastResponse;
                        }
                    });
        }
    }

}
