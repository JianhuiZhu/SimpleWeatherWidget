package com.jianhui_zhu.simpleweatherwidget.dataprovider;

import android.content.Context;
import android.util.Log;

import com.jianhui_zhu.simpleweatherwidget.CacheUtil;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.Util;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Daily;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.AirQualityResponse;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.CurrentDataWrapper;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.DarkSkyWeatherForecastResponse;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.ResponseWrapper;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by jianhuizhu on 2017-01-26.
 */

public class WeatherManagerImpl implements WeatherManager {
    private String weatherLocationStringBuilder(double lat, double lon){
        return lat+","+lon;
    }
    private String airQualityLocationStringBuilder(double lat, double lon){return lat+";"+lon;}
    WeatherAPI weatherAPI;
    AirQualityAPI airQualityAPI;
    private boolean hasCacheForecastWeather(){return wrapper.hasWeatherForecast(); }

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
    public WeatherManagerImpl(WeatherAPI weatherAPI, AirQualityAPI airQualityAPI){
        this.weatherAPI = weatherAPI;
        this.airQualityAPI = airQualityAPI;
    }

    @Override
    public Observable<CurrentDataWrapper> getCurrentWeatherByGeo(double lat, double lon, final Context context) {
        if(isForecastCacheValid(lat,lon)){
            CurrentDataWrapper currentDataWrapper = new CurrentDataWrapper()
                    .withCurrently(wrapper.getCurrentWeatherForecast())
                    .withAirQualityData(wrapper.getAirQualityData());
            return Observable.just(currentDataWrapper);
        }else{
            return Observable.zip(getWeatherForecastUpdate(context, lat, lon), getAirQualityUpdate(context, lat, lon), new Func2<DarkSkyWeatherForecastResponse, AirQualityResponse, CurrentDataWrapper>() {
                @Override
                public CurrentDataWrapper call(DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse, AirQualityResponse airQualityResponse) {
                    wrapper.withAirQualityResponse(airQualityResponse).withDarkSkyDailyWeatherResponse(darkSkyWeatherForecastResponse);
                    CacheUtil.cacheWeatherForecast(context,wrapper);
                    ResponseWrapper temp = CacheUtil.getWeatherForecastFromCache(context);
                    if(temp != null) {
                        Log.d("sharePref", temp.toString());
                    }
                    return new CurrentDataWrapper()
                            .withCurrently(wrapper.getCurrentWeatherForecast())
                            .withAirQualityData(wrapper.getAirQualityData());
                }
            });
        }
    }

    @Override
    public Observable<Daily> getDailyWeatherForecastByGeo(double lat, double lon, final Context context) {
        if(isForecastCacheValid(lat,lon)){
            return Observable.just(wrapper.getDailyWeatherForecast());
        }else{
            return Observable.zip(getWeatherForecastUpdate(context, lat, lon), getAirQualityUpdate(context, lat, lon), new Func2<DarkSkyWeatherForecastResponse, AirQualityResponse, Daily>() {
                @Override
                public Daily call(DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse, AirQualityResponse airQualityResponse) {
                    wrapper.withAirQualityResponse(airQualityResponse).withDarkSkyDailyWeatherResponse(darkSkyWeatherForecastResponse);
                    CacheUtil.cacheWeatherForecast(context,wrapper);
                    ResponseWrapper temp = CacheUtil.getWeatherForecastFromCache(context);
                    if(temp != null) {
                        Log.d("sharePref", temp.toString());
                    }
                    return wrapper.getDailyWeatherForecast();
                }
            });
        }
    }

    private Observable<AirQualityResponse> getAirQualityUpdate(Context context, double lat, double lon){
        String airQualityApiKey = context.getString(R.string.aqicnapikey);
        return airQualityAPI.getAirQualityByGeo(airQualityLocationStringBuilder(lat,lon), airQualityApiKey)
                .doOnNext(new Action1<AirQualityResponse>() {
                    @Override
                    public void call(AirQualityResponse airQualityResponse) {
                        wrapper.withAirQualityResponse(airQualityResponse);
                    }
                });
    }

    private Observable<DarkSkyWeatherForecastResponse> getWeatherForecastUpdate(Context context,double lat, double lon){
        previousLatitude = lat;
        previousLongitude = lon;
        String location = weatherLocationStringBuilder(lat,lon);

        String weatherApiKey = context.getString(R.string.darkskyapikey);
        return weatherAPI.getDailyWeatherForecast(weatherApiKey,location).doOnNext(new Action1<DarkSkyWeatherForecastResponse>() {
            @Override
            public void call(DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse) {
                lastUpdateTime = System.currentTimeMillis();
                wrapper.withDarkSkyDailyWeatherResponse(darkSkyWeatherForecastResponse);
            }
        });
    }
}
