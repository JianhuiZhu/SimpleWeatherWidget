package com.jianhui_zhu.simpleweatherwidget.dagger;

import com.jianhui_zhu.simpleweatherwidget.detailweather.DetailActivity;
import com.jianhui_zhu.simpleweatherwidget.widget.SimpleWeatherReceiver;

import dagger.Component;

/**
 * Created by jianhuizhu on 2017-02-16.
 */
@Component(modules = ViewModelModule.class)
public interface ViewModelComponent {
    void inject(DetailActivity activity);
    void inject(SimpleWeatherReceiver receiver);
}
