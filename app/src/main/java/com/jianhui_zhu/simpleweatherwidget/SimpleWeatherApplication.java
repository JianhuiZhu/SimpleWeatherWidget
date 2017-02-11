package com.jianhui_zhu.simpleweatherwidget;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by jianhuizhu on 2017-02-10.
 */

public class SimpleWeatherApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(getClass().getSimpleName(),"application on create called");
        Fresco.initialize(this);
    }
}
