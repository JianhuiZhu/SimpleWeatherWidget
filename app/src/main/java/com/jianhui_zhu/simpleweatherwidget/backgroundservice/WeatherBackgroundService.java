package com.jianhui_zhu.simpleweatherwidget.backgroundservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jianhui_zhu.simpleweatherwidget.dagger.APIModule;
import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerServiceManagerComponent;
import com.jianhui_zhu.simpleweatherwidget.dagger.ManagerModule;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherManager;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Currently;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Daily;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.CurrentDataWrapper;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse.DarkSkyWeatherForecastResponse;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import static com.jianhui_zhu.simpleweatherwidget.BroadcastIntentHandler.*;
import static com.jianhui_zhu.simpleweatherwidget.PermissionUtil.isLocationPermissionGranted;


/**
 * Created by jianhuizhu on 2017-02-08.
 */

public class WeatherBackgroundService extends Service {
    @Inject
    WeatherManager manager;
    WrappedLocationManager locationManager;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (isWeatherUpdateRequestByWidget(intent)) {

                getBriefWeatherForecastForWidget();

            } else if (isWeatherUpdateRequestByActivity(intent)) {

                getDetailWeatherInfoForActivity();

            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerServiceManagerComponent.builder()
                .aPIModule(new APIModule())
                .managerModule(new ManagerModule())
                .build().inject(this);
        Fresco.initialize(this);
        locationManager = new WrappedLocationManager(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_QUERY_BRIEF);
        intentFilter.addAction(ACTION_QUERY_DETAIL);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(receiver);
    }
}
