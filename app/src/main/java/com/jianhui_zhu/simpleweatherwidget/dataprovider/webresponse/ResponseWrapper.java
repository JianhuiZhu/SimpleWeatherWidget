package com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Alert;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Currently;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Daily;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianhuizhu on 2017-02-16.
 */

public class ResponseWrapper {
    private DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse;

    public ResponseWrapper withDarkSkyDailyWeatherResponse(DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse) {
        this.darkSkyWeatherForecastResponse = darkSkyWeatherForecastResponse;
        return this;
    }

    public Currently getCurrentWeatherForecast(){
        return darkSkyWeatherForecastResponse.getCurrently();
    }

    public Daily getDailyWeatherForecast(){
        return darkSkyWeatherForecastResponse.getDaily();
    }

    public ArrayList<Alert> getAlert(){
        return darkSkyWeatherForecastResponse.getAlerts();
    }

    public boolean isWeatherForecastEmpty(){
        return darkSkyWeatherForecastResponse == null;
    }
}
