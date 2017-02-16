package com.jianhui_zhu.simpleweatherwidget.dagger;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherManager;
import com.jianhui_zhu.simpleweatherwidget.detailweather.ViewModelDetailActivity;
import com.jianhui_zhu.simpleweatherwidget.widget.ViewModelSimpleWeather;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jianhuizhu on 2017-02-16.
 */
@Module
public class ViewModelModule {
    @Provides
    public ViewModelSimpleWeather providesViewModelSimpleWeather( ) {
        return new ViewModelSimpleWeather();
    }

    @Provides
    public ViewModelDetailActivity providesViewModelDetailActivity(){
        return new ViewModelDetailActivity();
    }
}
