package com.jianhui_zhu.simpleweatherwidget.detailweather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.DailyDataPoint;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jianhui_zhu.simpleweatherwidget.Util.*;
import static com.jianhui_zhu.simpleweatherwidget.WeatherIconImageUtil.*;
/**
 * Created by jianhuizhu on 2017-01-24.
 */

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
    private AdapterPositionChangeListener listener;
    private List<DailyDataPoint> dailyWeatherInfo = new ArrayList<>();

    WeatherForecastAdapter(@NonNull AdapterPositionChangeListener listener, List<DailyDataPoint> dailyWeatherInfo){
        if(dailyWeatherInfo != null && !dailyWeatherInfo.isEmpty()){
            this.dailyWeatherInfo.addAll(dailyWeatherInfo);
        }
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_detail_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DailyDataPoint weatherInfo = dailyWeatherInfo.get(position);

        listener.changeToolbarTitle(new DateTime(weatherInfo.getTime() * 1000L));
        final Context context = holder.temperatureTextView.getContext();

        try {
            holder.weatherImage.setImageResource(getIconIdByWeatherIconCode(weatherInfo.getIcon()));
        } catch (Exception e) {
            holder.weatherImage.setImageResource(R.drawable.ic_place_holder);
            e.printStackTrace();
        }

        holder.temperatureTextView.setText(
                getTemperatureString(
                        context,
                        weatherInfo.getTemperature()));

        holder.temperatureMaxTextView.setText(
                getTemperatureString(
                        context,
                        weatherInfo.getTemperatureMax()
                )
        );

        holder.temperatureMinTextView.setText(
                getTemperatureString(
                        context,
                        weatherInfo.getTemperatureMin())
        );

        holder.humidityTextView.setText(
                getHumidityString(context,weatherInfo.getHumidity()
                )
        );

        holder.windSpeedTextView.setText(
                getWindSpeedString(context,weatherInfo.getWindSpeed())
        );

        holder.windDirectionTextView.setText(
                getWindDirectionString(context,weatherInfo.getWindBearing())
        );
    }


    @Override
    public int getItemCount() {
        return dailyWeatherInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.detail_weather_image)
        ImageView weatherImage;
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

    public void updateWeatherData(List<DailyDataPoint> weatherForecastList){
        if(weatherForecastList != null){
            this.dailyWeatherInfo.addAll(weatherForecastList);
            notifyDataSetChanged();
        }
    }

    public interface AdapterPositionChangeListener {
        void changeToolbarTitle(DateTime date);
    }
}
