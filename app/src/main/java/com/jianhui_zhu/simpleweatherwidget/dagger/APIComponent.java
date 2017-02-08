package com.jianhui_zhu.simpleweatherwidget.dagger;
import com.jianhui_zhu.simpleweatherwidget.SimpleWeatherReceiver;
import com.jianhui_zhu.simpleweatherwidget.WeatherService;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.WeatherManagerImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jianhuizhu on 2017-01-19.
 */
@Component(modules = APIModule.class)
public interface APIComponent {
    void inject(SimpleWeatherReceiver receiver);
    void inject(WeatherService service);

}