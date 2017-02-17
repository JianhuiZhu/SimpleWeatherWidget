package com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse;

import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.AirQualityData;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Currently;

/**
 * Created by jianhuizhu on 2017-02-16.
 */

public class CurrentDataWrapper {
    private Currently currently;
    private AirQualityData airQualityData;

    public Currently getCurrently() {
        return currently;
    }

    public CurrentDataWrapper withCurrently(Currently currently) {
        this.currently = currently;
        return this;
    }

    public AirQualityData getAirQualityData() {
        return airQualityData;
    }

    public CurrentDataWrapper withAirQualityData(AirQualityData airQualityData) {
        this.airQualityData = airQualityData;
        return this;
    }

    public boolean hasCurrentlyData(){
        return currently != null;
    }


    public boolean hasAirQualityData(){
        return airQualityData != null;
    }
}
