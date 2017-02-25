package com.jianhui_zhu.simpleweatherwidget.data_provider;

import android.content.Context;

import com.jianhui_zhu.simpleweatherwidget.utils.CacheUtil;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Daily;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.AirQualityResponse;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.CurrentDataWrapper;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.DarkSkyWeatherForecastResponse;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.ResponseWrapper;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func2;
import static com.jianhui_zhu.simpleweatherwidget.utils.CacheUtil.*;

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

    @Inject
    public WeatherManagerImpl(WeatherAPI weatherAPI, AirQualityAPI airQualityAPI){
        this.weatherAPI = weatherAPI;
        this.airQualityAPI = airQualityAPI;
    }

    @Override
    public Observable<CurrentDataWrapper> getCurrentWeatherByGeo(double lat, double lon, final Context context) {
        if(!isCacheExpired(context)){
            CurrentDataWrapper currentDataWrapper = new CurrentDataWrapper();
            ResponseWrapper responseWrapper = getWeatherForecastFromCache(context);
            currentDataWrapper
                    .withCurrently(responseWrapper.getCurrentWeatherForecast())
                    .withAirQualityData(responseWrapper.getAirQualityData());
            return Observable.just(currentDataWrapper);
        }else{
            return Observable.zip(getWeatherForecastUpdate(context, lat, lon), getAirQualityUpdate(context, lat, lon), new Func2<DarkSkyWeatherForecastResponse, AirQualityResponse, CurrentDataWrapper>() {
                @Override
                public CurrentDataWrapper call(DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse, AirQualityResponse airQualityResponse) {

                    ResponseWrapper wrapper = new ResponseWrapper();
                    wrapper.withAirQualityResponse(airQualityResponse).withDarkSkyDailyWeatherResponse(darkSkyWeatherForecastResponse);
                    cacheWeatherForecast(context,wrapper);
                    return new CurrentDataWrapper()
                            .withCurrently(wrapper.getCurrentWeatherForecast())
                            .withAirQualityData(wrapper.getAirQualityData());
                }
            });
        }
    }

    @Override
    public Observable<Daily> getDailyWeatherForecastByGeo(double lat, double lon, final Context context) {
        if(!isCacheExpired(context)){
            ResponseWrapper wrapper = getWeatherForecastFromCache(context);
            return Observable.just(wrapper.getDailyWeatherForecast());
        }else{
            return Observable
                    .zip(getWeatherForecastUpdate(context, lat, lon),
                            getAirQualityUpdate(context, lat, lon),
                            new Func2<DarkSkyWeatherForecastResponse, AirQualityResponse, Daily>() {
                @Override
                public Daily call(DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse, AirQualityResponse airQualityResponse) {
                    ResponseWrapper wrapper = new ResponseWrapper();
                    wrapper.withAirQualityResponse(airQualityResponse).withDarkSkyDailyWeatherResponse(darkSkyWeatherForecastResponse);
                    CacheUtil.cacheWeatherForecast(context,wrapper);
                    return wrapper.getDailyWeatherForecast();
                }
            });
        }
    }

    private Observable<AirQualityResponse> getAirQualityUpdate(Context context, double lat, double lon){
        String airQualityApiKey = context.getString(R.string.aqicnapikey);
        return airQualityAPI.getAirQualityByGeo(airQualityLocationStringBuilder(lat,lon), airQualityApiKey);
    }

    private Observable<DarkSkyWeatherForecastResponse> getWeatherForecastUpdate(Context context,double lat, double lon){
        String location = weatherLocationStringBuilder(lat,lon);

        String weatherApiKey = context.getString(R.string.darkskyapikey);
        return weatherAPI.getDailyWeatherForecast(weatherApiKey,location);
    }
}
