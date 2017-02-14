package com.jianhui_zhu.simpleweatherwidget.detailweather;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import static com.jianhui_zhu.simpleweatherwidget.WeatherConstant.*;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.forecast.List;
import java.util.ArrayList;

/**
 * Created by jianhuizhu on 2017-02-07.
 */

public class ViewModelDetailActivity {


    public ViewModelDetailActivity(){

    }

    public void init(final DetailActivity activity, Toolbar toolbar, RecyclerView recyclerView, Intent intent){
        ArrayList<List> weatherList = intent.getParcelableArrayListExtra(WEATHER_LIST);
        recyclerView.setAdapter(new WeatherForecastAdapter(activity, weatherList));
        recyclerView.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        toolbar.inflateMenu(R.menu.detail_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.setting_option){
                    activity.getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.activity_detail,new WeatherPreferenceFragment())
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }

    public void refreshWeatherForecast(RecyclerView recyclerView, Intent intent){
        ArrayList<List> weatherList = intent.getParcelableArrayListExtra(WEATHER_LIST);
        ((WeatherForecastAdapter)recyclerView.getAdapter()).updateWeatherData(weatherList);
    }

}
