package com.jianhui_zhu.simpleweatherwidget.air_quality_detail;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Iaqi;

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

    public void initToolbar(Context context){

    }


}
