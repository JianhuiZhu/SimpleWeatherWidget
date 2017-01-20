package com.jianhui_zhu.simpleweatherwidget.model;

import android.content.Context;

import com.jianhui_zhu.simpleweatherwidget.model.Response.CurrentWeatherBriefResponse;
import com.jianhui_zhu.simpleweatherwidget.model.Response.CurrentWeatherFullResponse;

import java.util.List;

import rx.Observable;


/**
 * Created by jianhuizhu on 2017-01-17.
 */

public interface WeatherManager {

    Observable<CurrentWeatherBriefResponse> getCurrentWeatherBriefByGeo(Context context, double latitude, double longitude);

    Observable<List<CurrentWeatherFullResponse>> getCurrentWeatherFullByGeo(Context context, double latitude, double longitude);


}
