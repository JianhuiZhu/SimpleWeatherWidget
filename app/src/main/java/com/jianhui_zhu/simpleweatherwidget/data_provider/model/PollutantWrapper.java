package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

import com.jianhui_zhu.simpleweatherwidget.R;

/**
 * Created by jianhuizhu on 2017-02-25.
 */

public class PollutantWrapper {
    private final String name;
    private final Spanned htmlFormattedName;
    private final Spanned velocity;
    public PollutantWrapper(Context context,Pollutant pollutant){
        if(pollutant instanceof Co){
            htmlFormattedName = Html.fromHtml(context.getString(R.string.co)+"(CO)");
            name = context.getString(R.string.co);
        }else if(pollutant instanceof No2){
            htmlFormattedName = Html.fromHtml(context.getString(R.string.no2)+"(NO<sub>2</sub>)");
            name = context.getString(R.string.no2);
        }else if(pollutant instanceof O3){
            htmlFormattedName = Html.fromHtml(context.getString(R.string.o3)+"(O<sub>3</sub>)");
            name = context.getString(R.string.o3);
        }else if(pollutant instanceof So2){
            htmlFormattedName = Html.fromHtml(context.getString(R.string.so2)+"(SO<sub>2</sub>)");
            name = context.getString(R.string.so2);
        }else if(pollutant instanceof Pm10){
            htmlFormattedName = Html.fromHtml(context.getString(R.string.pm10)+"(PM<sub>10</sub>)");
            name = context.getString(R.string.pm10);
        }else if(pollutant instanceof Pm25){
            htmlFormattedName = Html.fromHtml(context.getString(R.string.pm2_5)+"(PM<sub>2.5</sub>)");
            name = context.getString(R.string.pm2_5);
        }else{
            htmlFormattedName = Html.fromHtml(pollutant.getClass().getSimpleName());
            name = pollutant.getClass().getSimpleName();
        }
        velocity = Html.fromHtml(String.valueOf(pollutant.getV()+"μg/m<sup>3</sup>"));
    }

    public Spanned getVelocity() {
        return velocity;
    }

    public Spanned getHtmlFormattedName() {
        return htmlFormattedName;
    }

    public String getName(){return name;}
}
