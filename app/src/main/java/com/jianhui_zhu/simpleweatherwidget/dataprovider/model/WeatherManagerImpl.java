package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import android.content.Context;
import android.util.Log;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.Util;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.current.CurrentWeatherResponse;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.forecast.FiveDayWeatherForecastResponse;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by jianhuizhu on 2017-01-26.
 */

public class WeatherManagerImpl implements WeatherManager {
    private boolean hasCacheCurrentWeather(){
        return currentWeather != null;
    }
    private boolean isCurrentWeatherCacheExpired(long currentTime){
        return currentTime - lastCurrentWeatherUpdate > UPDATE_EXPIRE_TIME;
    }
    private boolean isUserTravelLongDistance(double lat, double lon){
        return Util.GetDistance(previousLatitude, previousLongitude,lat,lon) > MAX_EXPIRE_DISTANCE;
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

    WeatherAPI api;
    private double previousLatitude;
    private double previousLongitude;
    private long lastCurrentWeatherUpdate = Long.MAX_VALUE;
    private long lastFiveDayWeatherUpdate = Long.MAX_VALUE;
    private CurrentWeatherResponse currentWeather;

    private FiveDayWeatherForecastResponse fiveDayWeather;
    @Inject
    public WeatherManagerImpl(WeatherAPI api){
        this.api = api;
    }

    @Override
    public Observable<CurrentWeatherResponse> getCurrentWeatherByGeo(double lat, double lon, Context context) {
        Log.d(getClass().getSimpleName(),"start get current weather");
        if(api == null){
            Log.e(getClass().getSimpleName(),"api is null");
        }
        long currentTime = System.currentTimeMillis();
        if(isCacheValid(currentTime,lat,lon)){
            return Observable.just(this.currentWeather);
        }else{
            lastCurrentWeatherUpdate = currentTime;
            return api.getCurrentWeatherForecast(lat,lon,context.getString(R.string.apikey))
                    .doOnNext(new Action1<CurrentWeatherResponse>() {
                        @Override
                        public void call(CurrentWeatherResponse currentWeatherResponse) {
                            Log.d(getClass().getSimpleName(),"weather data gotcha");
                            currentWeather = currentWeatherResponse;
                        }
                    })
                    .onErrorReturn(new Func1<Throwable, CurrentWeatherResponse>() {
                        @Override
                        public CurrentWeatherResponse call(Throwable throwable) {
                            Log.e(getClass().getSimpleName(),CURRENT_WEATHER_QUERY_ERROR,throwable);
                            return null;
                        }
                    });
        }
    }

    @Override
    public Observable<FiveDayWeatherForecastResponse> getFiveDayWeatherForecastByGeo(double lat, double lon, Context context) {
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
                    .doOnNext(new Action1<FiveDayWeatherForecastResponse>() {
                        @Override
                        public void call(FiveDayWeatherForecastResponse fiveDayWeatherForecastResponse) {
                            Log.d(getClass().getSimpleName(),"weather data gotcha");
                            lastFiveDayWeatherUpdate = currentTime;
                            fiveDayWeather = fiveDayWeatherForecastResponse;
                        }
                    })
                    .onErrorReturn(new Func1<Throwable, FiveDayWeatherForecastResponse>() {
                        @Override
                        public FiveDayWeatherForecastResponse call(Throwable throwable) {
                            Log.e(getClass().getSimpleName(),FIVE_DAY_WEATHER_QUERY_ERROR,throwable);
                            return null;
                        }
                    });
        }
    }
}
