package com.jianhui_zhu.simpleweatherwidget.air_quality_detail;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AddressResult;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AirQualityData;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.City;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Iaqi;
import com.jianhui_zhu.simpleweatherwidget.utils.AirQualityUtil;
import com.jianhui_zhu.simpleweatherwidget.utils.DateTimeUtil;

import org.joda.time.LocalDate;

/**
 * Created by jianhuizhu on 2017-02-26.
 */

public class ViewModelAirQuality {
    public void initPollutantsList(Context context, Iaqi pollutantsData, RecyclerView pollutantsList){

        AirPollutantAdapter adapter = new AirPollutantAdapter(context,pollutantsData);
        pollutantsList.setAdapter(adapter);

        pollutantsList.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        pollutantsList.setItemAnimator(new DefaultItemAnimator());

    }


    public void initLocationInfo(Context context, TextView locationName, TextView time, City city){
        locationName.setText(city.getName());

        String date = DateTimeUtil.getDateWithProperFormat(context,System.currentTimeMillis());
        time.setText(date);
    }

    public void initAQIInfo(Context context, TextView aqiNumber, TextView aqiDescription, int aqi){

        aqiNumber.setText(String.valueOf(aqi));

        String description = AirQualityUtil.getAirQualityDescriptionByAQI(context,aqi);
        aqiDescription.setText(description);
    }


}
