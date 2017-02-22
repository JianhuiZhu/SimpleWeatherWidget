package com.jianhui_zhu.simpleweatherwidget.backgroundservice;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;

import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerServiceManagerComponent;
import com.jianhui_zhu.simpleweatherwidget.dagger.ManagerModule;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherManager;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Daily;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import static com.jianhui_zhu.simpleweatherwidget.BroadcastIntentHandler.broadcastDetailWeatherUpdateForActivity;
import static com.jianhui_zhu.simpleweatherwidget.BroadcastIntentHandler.broadcastLocationPermissionNeededForCaller;
import static com.jianhui_zhu.simpleweatherwidget.PermissionUtil.isLocationPermissionGranted;

/**
 * Created by jianhuizhu on 2017-02-21.
 */

public class WeatherDetailService extends IntentService {
    @Inject
    WeatherManager manager;
    WrappedLocationManager locationManager;


    private void getDetailWeatherInfoForActivity() {
        if (!isLocationPermissionGranted(this)) {
            broadcastLocationPermissionNeededForCaller(this);
        } else {
            locationManager.getLocation().doOnError(new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    throwable.printStackTrace();
                }
            }).flatMap(new Func1<Location, Observable<Daily>>() {
                @Override
                public Observable<Daily> call(Location location) {
                    return manager.getDailyWeatherForecastByGeo(location.getLatitude(),location.getLongitude(),getApplicationContext());
                }
            }).doOnError(new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    throwable.printStackTrace();
                }
            }).subscribe(new Action1<Daily>() {
                @Override
                public void call(Daily daily) {
                    broadcastDetailWeatherUpdateForActivity(
                            getApplicationContext(),
                            daily);
                }
            });
        }
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public WeatherDetailService() {
        super("Weather detail service");
        DaggerServiceManagerComponent.builder().managerModule(new ManagerModule()).build().inject(this);
        locationManager = new WrappedLocationManager(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        getDetailWeatherInfoForActivity();
    }
}
