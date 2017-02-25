package com.jianhui_zhu.simpleweatherwidget.daily_weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.utils.DateTimeUtil;
import com.jianhui_zhu.simpleweatherwidget.utils.WeatherUtil;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.DailyDataPoint;


import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jianhui_zhu.simpleweatherwidget.utils.WeatherUtil.*;
import static com.jianhui_zhu.simpleweatherwidget.utils.WeatherIconImageUtil.*;
/**
 * Created by jianhuizhu on 2017-01-24.
 */

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
    private List<DailyDataPointWrapper> dailyWeatherInfo = new ArrayList<>();

    WeatherForecastAdapter(List<DailyDataPoint> dailyWeatherInfo,Context context){
        updateWeatherData(dailyWeatherInfo,context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String weekDay = dailyWeatherInfo.get(position).weekDay;
        String date = dailyWeatherInfo.get(position).date;
        DailyDataPoint weatherInfo = dailyWeatherInfo.get(position).dataPoint;


        final Context context = holder.weatherIcon.getContext();

        try {
            holder.weatherIcon.setImageResource(getIconIdByWeatherIconCode(weatherInfo.getIcon()));
        } catch (Exception e) {
            holder.weatherIcon.setImageResource(R.drawable.ic_place_holder);
            e.printStackTrace();
        }

        holder.date.setText(date);
        holder.weekday.setText(weekDay);

        String maxMinTemperature = getFormattedMaxMinTemperature(context,weatherInfo.getTemperatureMax(),weatherInfo.getTemperatureMin());
        holder.maxMinTemperature.setText(maxMinTemperature);
    }


    @Override
    public int getItemCount() {
        return dailyWeatherInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.weather_icon_image_view)
        ImageView weatherIcon;
        @BindView(R.id.weekday_text_view)
        TextView weekday;
        @BindView(R.id.date_text_view)
        TextView date;
        @BindView(R.id.temperature_max_min_text_view)
        TextView maxMinTemperature;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void updateWeatherData(List<DailyDataPoint> weatherForecastList,Context context){
        if(weatherForecastList != null && !weatherForecastList.isEmpty()){
            dailyWeatherInfo.clear();
            for(DailyDataPoint dataPoint : weatherForecastList){
                String weekDay = DateTimeUtil.getWeekDay(context,dataPoint.getTime());
                String date = DateTimeUtil.getDateWithProperFormat(context,dataPoint.getTime());
                dailyWeatherInfo.add(new DailyDataPointWrapper(dataPoint,weekDay,date));
            }
            notifyDataSetChanged();
        }
    }

    private class DailyDataPointWrapper{
        final DailyDataPoint dataPoint;
        final String weekDay;
        final String date;

        public DailyDataPointWrapper(DailyDataPoint dataPoint, String weekDay, String date){
            this.dataPoint = dataPoint;
            this.weekDay = weekDay;
            this.date = date;
        }
    }

}
