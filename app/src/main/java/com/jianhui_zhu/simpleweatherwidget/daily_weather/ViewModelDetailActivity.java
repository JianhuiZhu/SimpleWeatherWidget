package com.jianhui_zhu.simpleweatherwidget.daily_weather;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.jianhui_zhu.simpleweatherwidget.utils.WeatherConstant.*;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.utils.WeatherUtil;
import com.jianhui_zhu.simpleweatherwidget.utils.WeatherIconImageUtil;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Daily;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.DailyDataPoint;

import java.util.List;

/**
 * Created by jianhuizhu on 2017-02-07.
 */

public class ViewModelDetailActivity {


    public ViewModelDetailActivity(){

    }

    public void initToolbar(
                     final DetailActivity activity,
                     Toolbar toolbar){

        toolbar.setTitle(activity.getString(R.string.today));
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setSubtitleTextColor(Color.WHITE);

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
                            .addToBackStack(WeatherPreferenceFragment.class.getSimpleName())
                            .replace(R.id.activity_detail,new WeatherPreferenceFragment())
                            .commit();
                    return true;
                }
                return false;
            }
        });


    }

    public void initRecyclerView(Context context,RecyclerView recyclerView){
        recyclerView.setAdapter(new WeatherForecastAdapter(null,context));
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void initAdView(AdView adView){
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("5D8F2A9F0436C8AB8B996771EFDA3ECA").build();
        adView.loadAd(adRequest);
    }

    public void initTodayCardView(Context context, CardView todayCardView, TextView temperature,
                                  TextView maxTemperature, TextView minTemperature, ImageView weatherIcon,DailyDataPoint dataPoint){

            temperature.setText(WeatherUtil.getTemperatureString(context, dataPoint.getTemperature()));
            maxTemperature.setText(WeatherUtil.getTemperatureString(context,dataPoint.getTemperatureMax()));
            minTemperature.setText(WeatherUtil.getTemperatureString(context, dataPoint.getTemperatureMin()));

            String weatherCode = dataPoint.getIcon();
        try {
            int weatherId = WeatherIconImageUtil.getIconIdByWeatherIconCode(weatherCode);
            weatherIcon.setImageResource(weatherId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        todayCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void refreshWeatherForecast(RecyclerView recyclerView, Intent intent, Context context){
        List<DailyDataPoint> weatherList = ((Daily)intent.getParcelableExtra(WEATHER_LIST)).getData();
        ((WeatherForecastAdapter)recyclerView.getAdapter()).updateWeatherData(weatherList,context);
    }

}
