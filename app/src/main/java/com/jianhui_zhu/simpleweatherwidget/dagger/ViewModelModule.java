package com.jianhui_zhu.simpleweatherwidget.dagger;

import com.jianhui_zhu.simpleweatherwidget.daily_weather.ViewModelDetailActivity;
import com.jianhui_zhu.simpleweatherwidget.widget.ViewModelSimpleWeather;

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
}
