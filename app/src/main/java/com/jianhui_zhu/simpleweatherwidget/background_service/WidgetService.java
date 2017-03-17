package com.jianhui_zhu.simpleweatherwidget.background_service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;

import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerServiceManagerComponent;
import com.jianhui_zhu.simpleweatherwidget.dagger.ManagerModule;
import com.jianhui_zhu.simpleweatherwidget.manager.LocationManager;
import com.jianhui_zhu.simpleweatherwidget.manager.WeatherManager;
import com.jianhui_zhu.simpleweatherwidget.manager.LocationManagerImpl;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.CurrentDataWrapper;

import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.*;
import static com.jianhui_zhu.simpleweatherwidget.utils.PermissionUtil.isLocationPermissionGranted;

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
    LocationManager locationManager;
    private void getBriefWeatherForecastForWidget() {
        if (!isLocationPermissionGranted(this)) {

            broadcastLocationPermissionNeededForCaller(this);

        } else {
            locationManager.getLocation().doOnError(Throwable::printStackTrace)
                    .flatMap(location -> manager.getCurrentWeatherByGeo(
                            location.getLatitude(),location.getLongitude(),getApplicationContext()))
                    .doOnError(Throwable::printStackTrace)
                    .subscribe(currently -> broadcastBriefWeatherUpdateForWidget(
                    getApplicationContext(),
                    currently));
        }
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public WidgetService() {
        super("Widget Service");
        DaggerServiceManagerComponent.builder().managerModule(new ManagerModule()).build().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        locationManager =new LocationManagerImpl(this);
        getBriefWeatherForecastForWidget();
    }
}
