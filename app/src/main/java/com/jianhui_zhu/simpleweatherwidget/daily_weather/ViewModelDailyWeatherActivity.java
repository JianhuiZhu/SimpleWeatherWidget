package com.jianhui_zhu.simpleweatherwidget.daily_weather;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.jianhui_zhu.simpleweatherwidget.utils.Constant.*;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AddressResult;
import com.jianhui_zhu.simpleweatherwidget.manager.LocationManager;
import com.jianhui_zhu.simpleweatherwidget.manager.LocationManagerImpl;
import com.jianhui_zhu.simpleweatherwidget.utils.DateTimeUtil;
import com.jianhui_zhu.simpleweatherwidget.utils.StringFormatUtil;
import com.jianhui_zhu.simpleweatherwidget.utils.WeatherIconImageUtil;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Daily;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.DailyDataPoint;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by jianhuizhu on 2017-02-07.
 */

public class ViewModelDailyWeatherActivity {
    private LocationManager locationManager;

    public ViewModelDailyWeatherActivity(Context context){
        locationManager = new LocationManagerImpl(context);
    }

    public void initToolbar(
            final DailyWeatherActivity activity,
            final Toolbar toolbar){

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

    public void updateTodayCardViewWithData(Context context, CardView todayCardView, TextView temperature,
                                            TextView maxTemperature, TextView minTemperature, ImageView weatherIcon, DailyDataPoint dataPoint){

            temperature.setText(StringFormatUtil.getTemperatureString(context, dataPoint.getTemperature()));
            maxTemperature.setText(StringFormatUtil.getTemperatureString(context,dataPoint.getTemperatureMax()));
            minTemperature.setText(StringFormatUtil.getTemperatureString(context, dataPoint.getTemperatureMin()));

            String weatherCode = dataPoint.getIcon();
        try {
            int weatherId = WeatherIconImageUtil.getIconIdByWeatherIconCode(weatherCode);
            Drawable weatherDrawable = context.getResources().getDrawable(weatherId);
            weatherDrawable.setColorFilter(context.getResources().getColor(R.color.md_white_1000),PorterDuff.Mode.DST_IN);
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

    public void updateToolbarWithData(Toolbar toolbar, AddressResult addressResult){
        toolbar.setTitle(addressResult.getFormattedAddress());
        toolbar.setSubtitle(DateTimeUtil.getDateWithProperFormat(toolbar.getContext(),System.currentTimeMillis()));
    }

}
