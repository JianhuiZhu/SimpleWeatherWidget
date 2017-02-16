package com.jianhui_zhu.simpleweatherwidget.dagger;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherAPI;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherManager;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherManagerImpl;

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
    public WeatherManager providesWeatherManager(WeatherAPI api){return new WeatherManagerImpl(api);}
}
