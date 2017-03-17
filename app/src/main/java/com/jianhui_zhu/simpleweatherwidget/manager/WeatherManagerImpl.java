package com.jianhui_zhu.simpleweatherwidget.manager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.jianhui_zhu.simpleweatherwidget.data_provider.AirQualityAPI;
import com.jianhui_zhu.simpleweatherwidget.data_provider.WeatherAPI;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AddressResult;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AirQualityData;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Alert;
import com.jianhui_zhu.simpleweatherwidget.utils.CacheUtil;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Daily;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.AirQualityResponse;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.CurrentDataWrapper;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.DarkSkyWeatherForecastResponse;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.ResponseWrapper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func3;

import static com.jianhui_zhu.simpleweatherwidget.utils.CacheUtil.*;
import static com.jianhui_zhu.simpleweatherwidget.utils.StringFormatUtil.*;

/**
 * Created by jianhuizhu on 2017-01-26.
 */

public class WeatherManagerImpl implements WeatherManager {

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
            return getWeatherAndAirQualityWithGeo(lat,lon,context)
                    .flatMap(responseWrapper -> Observable.just(new CurrentDataWrapper()
                            .withAirQualityData(responseWrapper.getAirQualityData())
                    .withCurrently(responseWrapper.getCurrentWeatherForecast())));
        }
    }

    @Override
    public Observable<Daily> getDailyWeatherForecastByGeo(double lat, double lon, final Context context) {
        if(!isCacheExpired(context)){
            ResponseWrapper wrapper = getWeatherForecastFromCache(context);
            return Observable.just(wrapper.getDailyWeatherForecast());
        }else{
            return getWeatherAndAirQualityWithGeo(lat,lon,context)
                    .flatMap(wrapper -> Observable.just(wrapper.getDailyWeatherForecast()));

        }
    }

    @Override
    public Observable<AirQualityData> getAirQualityByGeo(double lat, double lon, final Context context) {
        if(!isCacheExpired(context)){
            ResponseWrapper wrapper = getWeatherForecastFromCache(context);
            return Observable.just(wrapper.getAirQualityData());
        }else{
            return getAirQualityUpdate(context,lat,lon).flatMap(new Func1<AirQualityResponse, Observable<AirQualityData>>() {
                @Override
                public Observable<AirQualityData> call(AirQualityResponse airQualityResponse) {
                    ResponseWrapper wrapper = getWeatherForecastFromCache(context);
                    wrapper.withAirQualityResponse(airQualityResponse);
                    return Observable.just(airQualityResponse.getAirQualityData());
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

    private Observable<ResponseWrapper> getWeatherAndAirQualityWithGeo(double lat, double lon, final Context context){
        LocationManager locationManager = new LocationManagerImpl(context);
        return Observable
                .zip(getWeatherForecastUpdate(context, lat, lon),
                        getAirQualityUpdate(context, lat, lon),
                        locationManager.getAddressResult(context),
                        (darkSkyWeatherForecastResponse, airQualityResponse, addressResult) -> {
                            ResponseWrapper wrapper = new ResponseWrapper();
                            wrapper.withAirQualityResponse(airQualityResponse)
                                    .withDarkSkyDailyWeatherResponse(darkSkyWeatherForecastResponse)
                                    .withAddressResult(addressResult);
                            CacheUtil.cacheWeatherForecast(context,wrapper);
                            notifyUserForAlertIfNecessary(wrapper,context);

                            return wrapper;
                        });
    }




    private boolean hasAlert(ResponseWrapper wrapper){
        return wrapper.getAlert() != null && !wrapper.getAlert().isEmpty();
    }

    private void notifyUserForAlertIfNecessary(ResponseWrapper wrapper,final Context context){
        if(hasAlert(wrapper)){
            List<Alert> alerts = wrapper.getAlert();
            for(int index = 0; index < alerts.size(); index++){
                buildAndSendNotification(context,alerts.get(index),index);
            }
        }
    }

    private void buildAndSendNotification(Context context, Alert alert,int alertId){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(alert.getUri()));
        openBrowserIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingItent = PendingIntent
                .getActivities(
                        context, 0, new Intent[] {openBrowserIntent },
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = builder.setSmallIcon(R.drawable.ic_warning)
                .setContentTitle(alert.getTitle())
                .setContentText(alert.getDescription())
                .addAction(R.drawable.ic_launch,context.getString(R.string.detail),pendingItent)
                .setAutoCancel(true)
                .setPriority(Notification.DEFAULT_ALL)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(alertId,notification);
    }
}
