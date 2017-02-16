package com.jianhui_zhu.simpleweatherwidget.dagger;

import com.jianhui_zhu.simpleweatherwidget.backgroundservice.WeatherBackgroundService;

import dagger.Component;

/**
 * Created by jianhuizhu on 2017-02-16.
 */
@Component(modules = {ManagerModule.class,APIModule.class})
public interface ServiceManagerComponent {
    void inject(WeatherBackgroundService service);
}
