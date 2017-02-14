package com.jianhui_zhu.simpleweatherwidget.dagger;
import com.jianhui_zhu.simpleweatherwidget.backgroundservice.WeatherBackgroundService;
import com.jianhui_zhu.simpleweatherwidget.detailweather.DetailActivity;
import com.jianhui_zhu.simpleweatherwidget.widget.SimpleWeatherReceiver;

import dagger.Component;

/**
 * Created by jianhuizhu on 2017-01-19.
 */
@Component(modules = APIModule.class)
public interface APIComponent {
    void inject(SimpleWeatherReceiver receiver);
    void inject(WeatherBackgroundService service);
    void inject(DetailActivity activity);
}