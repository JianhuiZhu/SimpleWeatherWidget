package com.jianhui_zhu.simpleweatherwidget.detailweather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;
import com.jianhui_zhu.simpleweatherwidget.BuildConfig;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.forecast.List;

import org.joda.time.DateTime;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jianhui_zhu.simpleweatherwidget.Util.*;

/**
 * Created by jianhuizhu on 2017-01-24.
 */

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
    private AdapterPositionChangeListener listener;
    private java.util.List<List> weatherForecastList = new ArrayList<>();

    WeatherForecastAdapter(@NonNull AdapterPositionChangeListener listener, java.util.List<List> weatherForecastList){
        if(weatherForecastList != null && !weatherForecastList.isEmpty()){
            this.weatherForecastList.addAll(weatherForecastList);
        }
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_detail_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List weatherInfo = weatherForecastList.get(position);

        listener.changeToolbarTitle(new DateTime(weatherInfo.getDt() * 1000L));
        final Context context = holder.temperatureTextView.getContext();

        holder.weatherImage.setImageURI(
                BuildConfig.WEATHER_ICON_BASE_URL+weatherInfo.getWeather().get(0).getIcon()+".png");

        holder.temperatureTextView.setText(
                getTemperatureString(
                        context,
                        weatherInfo.getMain().getTemp()));

        holder.temperatureMaxTextView.setText(
                getTemperatureString(
                        context,
                        weatherInfo.getMain().getTempMax()
                )
        );

        holder.temperatureMinTextView.setText(
                getTemperatureString(
                        context,
                        weatherInfo.getMain().getTempMin())
        );

        holder.humidityTextView.setText(
                getHumidityString(context,weatherInfo.getMain().getHumidity())
        );

        holder.windSpeedTextView.setText(
                getWindSpeedString(context,weatherInfo.getWind())
        );

        holder.windDirectionTextView.setText(
                getWindDirectionString(context,weatherInfo.getWind())
        );
    }


    @Override
    public int getItemCount() {
        return weatherForecastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.detail_weather_image)
        SimpleDraweeView weatherImage;
        @BindView(R.id.temperature_text_view)
        TextView temperatureTextView;
        @BindView(R.id.temperature_max_text_view)
        TextView temperatureMaxTextView;
        @BindView(R.id.temperature_min_text_view)
        TextView temperatureMinTextView;
        @BindView(R.id.humidity_value_text_view)
        TextView humidityTextView;
        @BindView(R.id.wind_speed_value_text_view)
        TextView windSpeedTextView;
        @BindView(R.id.wind_direction_text_view)
        TextView windDirectionTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void updateWeatherData(ArrayList<List> weatherForecastList){
        if(weatherForecastList != null){
            this.weatherForecastList.addAll(weatherForecastList);
            notifyDataSetChanged();
        }
    }

    public interface AdapterPositionChangeListener {
        void changeToolbarTitle(DateTime date);
    }
}
