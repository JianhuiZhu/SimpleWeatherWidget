package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

import com.jianhui_zhu.simpleweatherwidget.R;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by jianhuizhu on 2017-02-25.
 */

public class PollutantWrapper {
    private Spanned name;
    private Spanned velocity;
    public PollutantWrapper(Context context,Pollutant pollutant){
        setNameByPollutantType(context,pollutant);
    }

    private void setNameByPollutantType(Context context,Pollutant pollutant){
        if(pollutant instanceof Co){
            name = Html.fromHtml(context.getString(R.string.co)+"(CO)");
        }else if(pollutant instanceof No2){
            name = Html.fromHtml(context.getString(R.string.no2)+"(NO<sub>2</sub>)");
        }else if(pollutant instanceof O3){
            name = Html.fromHtml(context.getString(R.string.o3)+"(O<sub>3</sub>)");
        }else if(pollutant instanceof So2){
            name = Html.fromHtml(context.getString(R.string.so2)+"(SO<sub>2</sub>)");
        }else if(pollutant instanceof Pm10){
            name = Html.fromHtml(context.getString(R.string.pm10)+"(PM<sub>10</sub>)");
        }else if(pollutant instanceof Pm25){
            name = Html.fromHtml(context.getString(R.string.pm2_5)+"(PM<sub>2.5</sub>)");
        }
        velocity = Html.fromHtml(String.valueOf(pollutant.getV()+"μg/m<sup>3</sup>"));
    }

    public Spanned getVelocity() {
        return velocity;
    }

    public Spanned getName() {
        return name;
    }
}
