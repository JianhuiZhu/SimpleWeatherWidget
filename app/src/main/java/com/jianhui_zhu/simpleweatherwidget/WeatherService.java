package com.jianhui_zhu.simpleweatherwidget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.jianhui_zhu.simpleweatherwidget.dagger.APIModule;
import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerAPIComponent;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.WeatherManager;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Main;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.current.CurrentWeatherResponse;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

import static com.jianhui_zhu.simpleweatherwidget.Util.HUMIDITY;
import static com.jianhui_zhu.simpleweatherwidget.Util.MAX_TEMPERATURE;
import static com.jianhui_zhu.simpleweatherwidget.Util.MIN_TEMPERATURE;
import static com.jianhui_zhu.simpleweatherwidget.Util.TEMPERATURE;

/**
 * Created by jianhuizhu on 2017-02-03.
 */

public class WeatherService extends IntentService {
    @Inject
    WeatherManager manager;


    private Location getLocation(LocationManager locationManager){
        List<String> providers = locationManager.getAllProviders();
        for(String provider : providers){
            //noinspection MissingPermission
            Location location = locationManager.getLastKnownLocation(provider);
            if(location != null){
                return location;
            }
        }
        return null;
    }

    public WeatherService() {
        super("WeatherService");
        DaggerAPIComponent.builder().aPIModule(new APIModule()).build().inject(this);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if(isCurrentQueryWeather(intent)){
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if(PermissionUtil.isLocationPermissionGranted(this)){
                //noinspection MissingPermission
                Location location = getLocation(locationManager);
                if(location == null){
                    //noinspection MissingPermission
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000, 5000, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            manager.getCurrentWeatherByGeo(location.getLatitude(),location.getLongitude(),getApplicationContext())
                                    .subscribe(new Action1<CurrentWeatherResponse>() {
                                        @Override
                                        public void call(CurrentWeatherResponse currentWeatherResponse) {
                                            broadcastCurrentWeatherUpdate(currentWeatherResponse);
                                        }
                                    });

                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }, Looper.getMainLooper());
                }else{
                    manager.getCurrentWeatherByGeo(location.getLatitude(),location.getLongitude(),this)
                            .subscribe(new Action1<CurrentWeatherResponse>() {
                                @Override
                                public void call(CurrentWeatherResponse currentWeatherResponse) {
                                    broadcastCurrentWeatherUpdate(currentWeatherResponse);
                                }
                            });
                }
            }
        }



    }

    private void broadcastCurrentWeatherUpdate(CurrentWeatherResponse currentWeatherResponse){
        Intent intent = new Intent(Constant.ACTION_UPDATE);
        Main main = currentWeatherResponse.getMain();

        intent.putExtra(MAX_TEMPERATURE,String.valueOf(main.getTempMax()));
        intent.putExtra(MIN_TEMPERATURE,String.valueOf(main.getTempMin()));
        intent.putExtra(HUMIDITY,String.valueOf(main.getHumidity()+"%"));
        intent.putExtra(TEMPERATURE, String.valueOf(main.getTemp()));
        sendBroadcast(intent);
    }


    private boolean isCurrentQueryWeather(Intent intent){
        if(intent != null
                && intent.getAction().equals(Constant.ACTION_QUERY_CURRENT)){
            return true;
        }
        return false;
    }
}
