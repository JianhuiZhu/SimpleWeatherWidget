package com.jianhui_zhu.simpleweatherwidget.backgroundservice;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;

import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerServiceManagerComponent;
import com.jianhui_zhu.simpleweatherwidget.dagger.ManagerModule;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherManager;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.CurrentDataWrapper;

import static com.jianhui_zhu.simpleweatherwidget.BroadcastIntentHandler.*;
import static com.jianhui_zhu.simpleweatherwidget.PermissionUtil.isLocationPermissionGranted;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by jianhuizhu on 2017-02-21.
 */

public class WidgetService extends IntentService {
    @Inject
    WeatherManager manager;
    WrappedLocationManager locationManager;
    private void getBriefWeatherForecastForWidget() {
        if (!isLocationPermissionGranted(this)) {

            broadcastLocationPermissionNeededForCaller(this);

        } else {
            locationManager.getLocation().doOnError(new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    throwable.printStackTrace();
                }
            }).flatMap(new Func1<Location, Observable<CurrentDataWrapper>>() {
                @Override
                public Observable<CurrentDataWrapper> call(Location location) {
                    return manager.getCurrentWeatherByGeo(location.getLatitude(),location.getLongitude(),getApplicationContext());
                }
            }).doOnError(new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    throwable.printStackTrace();
                }
            }).subscribe(new Action1<CurrentDataWrapper>() {
                @Override
                public void call(CurrentDataWrapper currently) {
                    broadcastBriefWeatherUpdateForWidget(
                            getApplicationContext(),
                            currently);
                }
            });
        }
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public WidgetService() {
        super("Widget Service");
        DaggerServiceManagerComponent.builder().managerModule(new ManagerModule()).build().inject(this);
        locationManager = new WrappedLocationManager(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(isWeatherUpdateRequestByWidget(intent)){
            getBriefWeatherForecastForWidget();
        }
    }
}
