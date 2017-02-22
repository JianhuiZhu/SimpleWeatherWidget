package com.jianhui_zhu.simpleweatherwidget.dagger;

import com.jianhui_zhu.simpleweatherwidget.backgroundservice.WeatherBackgroundService;
import com.jianhui_zhu.simpleweatherwidget.backgroundservice.WeatherDetailService;
import com.jianhui_zhu.simpleweatherwidget.backgroundservice.WidgetService;

import dagger.Component;

/**
 * Created by jianhuizhu on 2017-02-16.
 */
@Component(modules = {ManagerModule.class,APIModule.class})
public interface ServiceManagerComponent {
    void inject(WeatherBackgroundService service);
    void inject(WidgetService service);
    void inject(WeatherDetailService service);
}
