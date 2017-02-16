package com.jianhui_zhu.simpleweatherwidget.dataprovider;

import android.content.Context;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.Util;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.DarkSkyCurrentWeatherResponse;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.DarkSkyDailyWeatherResponse;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by jianhuizhu on 2017-01-26.
 */

public class WeatherManagerImpl implements WeatherManager {
    private String locationStringBuilder(double lat, double lon){
        return lat+","+lon;
    }
    WeatherAPI api;
    private boolean hasCacheCurrentWeather(){
        return currentWeather != null;
    }

    private boolean hasCacheForecastWeather(){return fiveDayWeather != null; }
    private boolean isCurrentWeatherCacheExpired(long currentTime){
        return currentTime - lastCurrentWeatherUpdate > UPDATE_EXPIRE_TIME;
    }
    private boolean isDetailWeatherCacheExpired(long currentTime){
        return currentTime - lastFiveDayWeatherUpdate > UPDATE_EXPIRE_TIME;
    }

    private boolean isUserTravelLongDistance(double lat, double lon){
        return Util.getDistance(previousLatitude, previousLongitude,lat,lon) > MAX_EXPIRE_DISTANCE;
    }
    private boolean isCurrentCacheValid(long currentTime, double lat, double lon){
        return hasCacheCurrentWeather() && (!isCurrentWeatherCacheExpired(currentTime) || !isUserTravelLongDistance(lat,lon));
    }

    private boolean isForecastCacheValid(long currentTime, double lat, double lon){
        return hasCacheForecastWeather() && (!isDetailWeatherCacheExpired(currentTime) || !isUserTravelLongDistance(lat,lon));
    }


    private final static String CURRENT_WEATHER_QUERY_ERROR = "CURRENT_WEATHER_ERROR";
    private final static String FIVE_DAYd_WEATHER_QUERY_ERROR = "FIVE_DAY_WEATHER_ERROR";
    //Default expire time 2 hour
    private final static long UPDATE_EXPIRE_TIME = 7200000;
    //Default expire distance 50km
    private final static double MAX_EXPIRE_DISTANCE = 50;


    private double previousLatitude;
    private double previousLongitude;
    private long lastCurrentWeatherUpdate = Long.MAX_VALUE;
    private long lastFiveDayWeatherUpdate = Long.MAX_VALUE;
    private DarkSkyCurrentWeatherResponse currentWeather;

    private DarkSkyDailyWeatherResponse fiveDayWeather;
    @Inject
    public WeatherManagerImpl(WeatherAPI api){
        this.api = api;
    }

    @Override
    public Observable<DarkSkyCurrentWeatherResponse> getCurrentWeatherByGeo(double lat, double lon, Context context) {
        final long currentTime = System.currentTimeMillis();
        if(isCurrentCacheValid(currentTime,lat,lon)){
            return Observable.just(this.currentWeather);
        }else{
            lastCurrentWeatherUpdate = currentTime;
            return api.getCurrentWeatherForecast(context.getString(R.string.darkskyapikey),locationStringBuilder(lat,lon))
                    .doOnNext(new Action1<DarkSkyCurrentWeatherResponse>() {
                        @Override
                        public void call(DarkSkyCurrentWeatherResponse darkSkyCurrentWeatherResponse) {
                            currentWeather = darkSkyCurrentWeatherResponse;
                            lastCurrentWeatherUpdate = currentTime;
                        }
                    });
        }
    }

    @Override
    public Observable<DarkSkyDailyWeatherResponse> getDailyWeatherForecastByGeo(double lat, double lon, Context context) {
        final long currentTime = System.currentTimeMillis();
        if(isForecastCacheValid(currentTime,lat,lon)){
            previousLatitude = lat;
            previousLongitude = lon;
            return Observable.just(this.fiveDayWeather);
        }else{
            previousLatitude = lat;
            previousLongitude = lon;
            return api.getDailyWeatherForecast(context.getString(R.string.darkskyapikey),locationStringBuilder(lat,lon))
                    .doOnNext(new Action1<DarkSkyDailyWeatherResponse>() {
                        @Override
                        public void call(DarkSkyDailyWeatherResponse detailWeatherForecastResponse) {
                            fiveDayWeather =detailWeatherForecastResponse;
                            lastFiveDayWeatherUpdate = currentTime;
                        }
                    });
        }
    }

}
