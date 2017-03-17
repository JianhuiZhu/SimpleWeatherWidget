package com.jianhui_zhu.simpleweatherwidget.background_service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.os.Parcelable;

import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerServiceManagerComponent;
import com.jianhui_zhu.simpleweatherwidget.dagger.ManagerModule;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AddressResult;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AirQualityData;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.DetailWeatherWrapper;
import com.jianhui_zhu.simpleweatherwidget.manager.LocationManager;
import com.jianhui_zhu.simpleweatherwidget.manager.WeatherManager;
import com.jianhui_zhu.simpleweatherwidget.manager.LocationManagerImpl;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Daily;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.broadcastDetailWeatherUpdateForActivity;
import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.broadcastLocationPermissionNeededForCaller;
import static com.jianhui_zhu.simpleweatherwidget.utils.PermissionUtil.isLocationPermissionGranted;

/**
 * Created by jianhuizhu on 2017-02-21.
 */

public class WeatherDetailService extends IntentService {
    @Inject
    WeatherManager manager;
    LocationManager locationManager;

    private void getDetailWeatherInfoForActivity() {
        if (!isLocationPermissionGranted(this)) {
            broadcastLocationPermissionNeededForCaller(this);
        } else {
            locationManager.getLocation()
                    .doOnError(Throwable::printStackTrace).subscribe(location -> {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                Observable.zip(
                        manager.getDailyWeatherForecastByGeo(lat, lon, getApplicationContext())
                        , locationManager.getAddressResult(getApplicationContext(), lat, lon)
                        , DetailWeatherWrapper::new)
                        .subscribe(
                                wrapper -> broadcastDetailWeatherUpdateForActivity(getApplicationContext(),wrapper));
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

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        locationManager = new LocationManagerImpl(this);
        getDetailWeatherInfoForActivity();
    }
}
