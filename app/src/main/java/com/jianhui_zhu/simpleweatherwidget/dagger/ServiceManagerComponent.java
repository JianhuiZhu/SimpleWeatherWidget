package com.jianhui_zhu.simpleweatherwidget.dagger;

import com.jianhui_zhu.simpleweatherwidget.background_service.WeatherDetailService;
import com.jianhui_zhu.simpleweatherwidget.background_service.WidgetService;

import dagger.Component;

/**
 * Created by jianhuizhu on 2017-02-16.
 */
@Component(modules = {ManagerModule.class,APIModule.class})
public interface ServiceManagerComponent {
    void inject(WidgetService service);
    void inject(WeatherDetailService service);
}
