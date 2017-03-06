package com.jianhui_zhu.simpleweatherwidget.air_quality_detail;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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
    @BindView(R.id.location_time_text_view)
    TextView locationTime;
    @BindView(R.id.location_name_text_view)
    TextView locationName;
    @BindView(R.id.aqi_number_text_view)
    TextView aqiNumber;
    @BindView(R.id.aqi_description_brief_text_view)
    TextView aqiDescription;
    @BindView(R.id.pollutants_recycler_view)
    RecyclerView pollutantsList;
    ViewModelAirQuality viewModel = new ViewModelAirQuality();
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_air_quality_detail);
        ButterKnife.bind(this);

        AirQualityData data = getIntent().getParcelableExtra(AIR_QUALITY);

        viewModel.initPollutantsList(this,data.getIaqi(),pollutantsList);
        viewModel.initLocationInfo(this,locationName,locationTime,data.getCity());
        viewModel.initAQIInfo(this,aqiNumber,aqiDescription,data.getAqi());
    }
}
