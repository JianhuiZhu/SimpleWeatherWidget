package com.jianhui_zhu.simpleweatherwidget.background_service;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import java.util.List;

import ru.solodovnikov.rxlocationmanager.RxLocationManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jianhuizhu on 2017-02-08.
 */

public class WrappedLocationManager {
    private RxLocationManager rxLocationManager;
    private LocationManager locationManager;

    public WrappedLocationManager(Context context) {
        rxLocationManager = new RxLocationManager(context);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    private Location getAnyLastKnownLocation() {
        List<String> providers = locationManager.getAllProviders();
        for (String provider : providers) {
            @SuppressWarnings("MissingPermission")
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                return location;
            }
        }
        return null;
    }

    private Observable<Location> requestLocationUpdate() {
        return rxLocationManager.requestLocation(LocationManager.GPS_PROVIDER)
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Location>>() {
                    @Override
                    public Observable<? extends Location> call(Throwable throwable) {
                        throwable.printStackTrace();
                        return rxLocationManager.requestLocation(LocationManager.NETWORK_PROVIDER);
                    }
                });
    }

    public Observable<Location> getLocation() {
        Location location = getAnyLastKnownLocation();
        if (location != null) {
            return Observable.just(location);
        }
        return requestLocationUpdate();
    }
}
