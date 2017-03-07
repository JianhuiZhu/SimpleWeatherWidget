package com.jianhui_zhu.simpleweatherwidget.dagger;

import com.jianhui_zhu.simpleweatherwidget.background_service.AirQualityService;
import com.jianhui_zhu.simpleweatherwidget.background_service.WeatherDetailService;
import com.jianhui_zhu.simpleweatherwidget.background_service.WidgetService;
import com.jianhui_zhu.simpleweatherwidget.daily_weather.ViewModelDailyWeatherActivity;
import com.jianhui_zhu.simpleweatherwidget.manager.LocationManagerImpl;

import dagger.Component;

/**
 * Created by jianhuizhu on 2017-02-16.
 */
@Component(modules = {ManagerModule.class,APIModule.class})
public interface ServiceManagerComponent {
    void inject(WidgetService service);
    void inject(WeatherDetailService service);
    void inject(AirQualityService service);
    void inject(LocationManagerImpl manager);
    void inject(ViewModelDailyWeatherActivity viewModelDailyWeatherActivity);
}
