package com.jianhui_zhu.simpleweatherwidget.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.jianhui_zhu.simpleweatherwidget.BuildConfig;
import com.jianhui_zhu.simpleweatherwidget.air_quality_detail.AirQualityActivity;
import com.jianhui_zhu.simpleweatherwidget.daily_weather.DailyWeatherActivity;

import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.ACTION_REQUEST_PERMISSION;

/**
 * Created by jianhuizhu on 2017-03-06.
 */

public final class ActivityFragmentUtil {
    public static void openBrowserToShowSearchResult(Context context, String query) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(BuildConfig.GOOGLE_SEARCH_URL + query);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static PendingIntent startPendingActivity(Context context, Class<?> clt) {
        Intent intent;
        if (clt.equals(DailyWeatherActivity.class)) {
            intent = new Intent(context, DailyWeatherActivity.class);

            if (!PermissionUtil.isLocationPermissionGranted(context)) {
                intent.setAction(ACTION_REQUEST_PERMISSION);
            }
        } else {
            intent = new Intent(context, AirQualityActivity.class);
        }

        return PendingIntent.getActivity(context, 0, intent, 0);
    }
}
