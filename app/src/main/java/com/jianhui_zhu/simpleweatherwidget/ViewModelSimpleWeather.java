package com.jianhui_zhu.simpleweatherwidget;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import com.jianhui_zhu.simpleweatherwidget.model.Response.CurrentWeatherBriefResponse;
import com.jianhui_zhu.simpleweatherwidget.model.WeatherManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by jianhuizhu on 2017-01-20.
 */

public class ViewModelSimpleWeather {
    public void refreshCurrentLocationWeather(WeatherManager weatherManager, Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setSpeedRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setAccuracy(Criteria.NO_REQUIREMENT);
        criteria.setAccuracy(Criteria.NO_REQUIREMENT);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setVerticalAccuracy(Criteria.NO_REQUIREMENT);

        String provider = locationManager.getBestProvider(criteria, true);
        if (provider == null) {
            //show alert message or use old data


        } else {
            Location location = locationManager.getLastKnownLocation(provider);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            weatherManager
                    .getCurrentWeatherBriefByGeo(context,latitude,longitude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<CurrentWeatherBriefResponse>() {
                        @Override
                        public void call(CurrentWeatherBriefResponse currentWeatherBriefResponse) {

                        }
                    });
        }

    }
}
