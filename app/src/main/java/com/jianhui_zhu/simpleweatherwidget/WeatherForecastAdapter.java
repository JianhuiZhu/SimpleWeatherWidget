package com.jianhui_zhu.simpleweatherwidget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.zetterstrom.com.forecast.models.DataBlock;
import android.zetterstrom.com.forecast.models.DataPoint;
import android.zetterstrom.com.forecast.models.Forecast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 2017-01-24.
 */

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
    private List<DataPoint> dailyForecastList = new ArrayList<>();
    WeatherForecastAdapter(List<DataPoint> dailyForecastList){
        if(dailyForecastList!=null) {
            this.dailyForecastList.addAll(dailyForecastList);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_detail_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataPoint dataPoint = dailyForecastList.get(position);
        Util.weatherIconSetter(null,0,holder.weatherImage,dataPoint);
        if(dataPoint.getTemperature()!=null) {
            holder.temperatureTextView.setText(Util.fahrenheitToCelsiusString(dataPoint.getTemperature()));
        }else if(dataPoint.getApparentTemperature()!=null){
            holder.temperatureTextView.setText(Util.fahrenheitToCelsiusString(dataPoint.getApparentTemperature()));
        }
    }


    @Override
    public int getItemCount() {
        return dailyForecastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.detail_weather_image)
        ImageView weatherImage;
        @BindView(R.id.temperature_text_view)
        TextView temperatureTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
