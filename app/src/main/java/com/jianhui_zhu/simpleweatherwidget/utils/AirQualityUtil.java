package com.jianhui_zhu.simpleweatherwidget.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.air_quality_detail.AirQualityActivity;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AirQualityData;

import static com.jianhui_zhu.simpleweatherwidget.utils.Constant.AIR_QUALITY;

/**
 * Created by jianhuizhu on 2017-02-17.
 */

public class AirQualityUtil {
    private static final int HAZARDOUS = 301;
    private static final int VERY_UNHEALTHY = 201;
    private static final int UNHEALTHY = 151;
    private static final int UNHEALTHY_FOR_SENSITIVE_GROUP = 101;
    private static final int MODERATE = 51;
    public static String getAirQualityDescriptionByAQI(Context context, int aqi){
        if(aqi >= HAZARDOUS){
            return context.getString(R.string.hazardous);
        } else if(aqi >= VERY_UNHEALTHY){
            return context.getString(R.string.very_unhealthy);
        }else if(aqi >= UNHEALTHY){
            return context.getString(R.string.unhealthy);
        }else if(aqi >= UNHEALTHY_FOR_SENSITIVE_GROUP){
            return context.getString(R.string.unhealthy_for_sensitive_groups);
        }else if(aqi >= MODERATE){
            return context.getString(R.string.moderate);
        }else{
            return context.getString(R.string.good);
        }
    }

    public static int getAirQualityBackgroundColorByAQI(Context context,int aqi){
        if(aqi >= HAZARDOUS){
            return context.getResources().getColor(R.color.aqi_hazardous);
        } else if(aqi >= VERY_UNHEALTHY){
            return context.getResources().getColor(R.color.aqi_very_unhealthy);
        }else if(aqi >= UNHEALTHY){
            return context.getResources().getColor(R.color.aqi_unhealthy);
        }else if(aqi >= UNHEALTHY_FOR_SENSITIVE_GROUP){
            return context.getResources().getColor(R.color.aqi_sensitive_group);
        }else if(aqi >= MODERATE){
            return context.getResources().getColor(R.color.aqi_moderate);
        }else{
            return context.getResources().getColor(R.color.aqi_good);
        }
    }

}
