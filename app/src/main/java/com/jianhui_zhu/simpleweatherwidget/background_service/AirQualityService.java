package com.jianhui_zhu.simpleweatherwidget.background_service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.Nullable;

import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerServiceManagerComponent;
import com.jianhui_zhu.simpleweatherwidget.dagger.ManagerModule;
import com.jianhui_zhu.simpleweatherwidget.manager.LocationManager;
import com.jianhui_zhu.simpleweatherwidget.manager.LocationManagerImpl;
import com.jianhui_zhu.simpleweatherwidget.manager.WeatherManager;
import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.broadcastAirQualityUpdate;
import javax.inject.Inject;

/**
 * Created by jianhuizhu on 2017-03-07.
 */

public class AirQualityService extends IntentService {
    @Inject
    WeatherManager manager;
    LocationManager locationManager;

    private void getAirQualityUpdateForActivity(){
        locationManager
                .getLocation()
                .flatMap(location ->manager.getAirQualityByGeo(location.getLatitude(),location.getLongitude(),getApplicationContext()))
                .subscribe(airQualityData -> broadcastAirQualityUpdate(getApplicationContext(),airQualityData));
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public AirQualityService() {
        super("Air quality service");
        DaggerServiceManagerComponent.builder().managerModule(new ManagerModule()).build().inject(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        locationManager = new LocationManagerImpl(this);
        getAirQualityUpdateForActivity();
    }
}
