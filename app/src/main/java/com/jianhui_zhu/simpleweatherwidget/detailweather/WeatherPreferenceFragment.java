package com.jianhui_zhu.simpleweatherwidget.detailweather;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.jianhui_zhu.simpleweatherwidget.R;

/**
 * Created by jianhuizhu on 2017-02-13.
 */

public class WeatherPreferenceFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preference);
    }
}
