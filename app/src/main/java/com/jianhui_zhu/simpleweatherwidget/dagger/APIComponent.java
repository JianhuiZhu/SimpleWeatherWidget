package com.jianhui_zhu.simpleweatherwidget.dagger;

import com.jianhui_zhu.simpleweatherwidget.SimpleWeatherReceiver;

import dagger.Component;

/**
 * Created by jianhuizhu on 2017-01-19.
 */

@Component(modules = APIModule.class)
public interface APIComponent {
    void inject(SimpleWeatherReceiver receiver);
}
