package com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse;

import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AirQualityData;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Alert;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Currently;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Daily;

import java.util.ArrayList;

/**
 * Created by jianhuizhu on 2017-02-16.
 */

public class ResponseWrapper {
    private DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse;
    private AirQualityResponse airQualityResponse;
    public ResponseWrapper withDarkSkyDailyWeatherResponse(DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse) {
        this.darkSkyWeatherForecastResponse = darkSkyWeatherForecastResponse;
        return this;
    }

    public DarkSkyWeatherForecastResponse getDarkSkyWeatherForecastResponse() {
        return darkSkyWeatherForecastResponse;
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "darkSkyWeatherForecastResponse=" + darkSkyWeatherForecastResponse.toString() +
                ", airQualityResponse=" + airQualityResponse.toString() +
                '}';
    }

    public void setDarkSkyWeatherForecastResponse(DarkSkyWeatherForecastResponse darkSkyWeatherForecastResponse) {
        this.darkSkyWeatherForecastResponse = darkSkyWeatherForecastResponse;
    }

    public AirQualityResponse getAirQualityResponse() {
        return airQualityResponse;
    }

    public void setAirQualityResponse(AirQualityResponse airQualityResponse) {
        this.airQualityResponse = airQualityResponse;
    }

    public ResponseWrapper withAirQualityResponse(AirQualityResponse airQualityResponse){
        this.airQualityResponse = airQualityResponse;
        return this;
    }

    public AirQualityData getAirQualityData(){
        return this.airQualityResponse.getAirQualityData();
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

    public boolean hasWeatherForecast(){
        return darkSkyWeatherForecastResponse != null;
    }

    public boolean hasAirQualityData(){
        return airQualityResponse != null;
    }
}
