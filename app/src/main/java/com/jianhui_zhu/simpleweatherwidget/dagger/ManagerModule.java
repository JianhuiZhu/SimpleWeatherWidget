package com.jianhui_zhu.simpleweatherwidget.dagger;

import android.content.Context;

import com.jianhui_zhu.simpleweatherwidget.data_provider.AirQualityAPI;
import com.jianhui_zhu.simpleweatherwidget.data_provider.GeoCodingAPI;
import com.jianhui_zhu.simpleweatherwidget.data_provider.WeatherAPI;
import com.jianhui_zhu.simpleweatherwidget.manager.LocationManager;
import com.jianhui_zhu.simpleweatherwidget.manager.LocationManagerImpl;
import com.jianhui_zhu.simpleweatherwidget.manager.WeatherManager;
import com.jianhui_zhu.simpleweatherwidget.manager.WeatherManagerImpl;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jianhuizhu on 2017-02-16.
 */
@Module
public class ManagerModule {
    @Provides
    @Inject
    public WeatherManager providesWeatherManager(WeatherAPI weatherAPI, AirQualityAPI airQualityAPI)
    {return new WeatherManagerImpl(weatherAPI,airQualityAPI);}

}
