package com.jianhui_zhu.simpleweatherwidget;

import android.content.Context;
import android.content.SharedPreferences;
import static com.jianhui_zhu.simpleweatherwidget.Util.*;
/**
 * Created by jianhuizhu on 2017-02-17.
 */

public class AirQualityUtil {
    private static final int VERY_UNHEALTHY = 201;
    private static final int UNHEALTHY = 151;
    private static final int UNHEALTHY_FOR_SENSITIVE_GROUP = 101;
    private static final int MODERATE = 51;
    public static String getAirQualityDescriptionByAQI(Context context, int aqi){
        if(aqi >= VERY_UNHEALTHY){
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
}
