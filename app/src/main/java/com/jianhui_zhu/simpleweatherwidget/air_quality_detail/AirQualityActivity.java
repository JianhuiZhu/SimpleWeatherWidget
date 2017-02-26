package com.jianhui_zhu.simpleweatherwidget.air_quality_detail;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AddressResult;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AirQualityData;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jianhui_zhu.simpleweatherwidget.utils.Constant.ADDRESS;
import static com.jianhui_zhu.simpleweatherwidget.utils.Constant.AIR_QUALITY;

/**
 * Created by jianhuizhu on 2017-02-25.
 */

public class AirQualityActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.aqi_card_view)
    CardView aqiCard;
    @BindView(R.id.pollutants_recycler_view)
    RecyclerView pollutantsList;
    ViewModelAirQuality viewModel = new ViewModelAirQuality();
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_air_quality_detail);
        ButterKnife.bind(this);

        AirQualityData data = getIntent().getParcelableExtra(AIR_QUALITY);
        AddressResult result = getIntent().getParcelableExtra(ADDRESS);

        viewModel.initPollutantsList(this,data.getIaqi(),pollutantsList);
    }
}
