package com.jianhui_zhu.simpleweatherwidget.air_quality_detail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AirQualityData;
import com.jianhui_zhu.simpleweatherwidget.utils.ProgressDialogWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.ACTION_AIR_QUALITY_ACTIVITY_UPDATE;
import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.ACTION_WEATHER_ACTIVITY_UPDATE;
import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.isAirQualityUpdateForActivity;
import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.startServiceForAirQualityUpdateRequest;
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
    ProgressDialogWrapper progressDialog;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(isAirQualityUpdateForActivity(intent)){
                AirQualityData data = intent.getParcelableExtra(AIR_QUALITY);

                Gson gson = new Gson();
                Log.d("Test result",gson.toJson(data));
                viewModel.initPollutantsList(getApplicationContext(),data.getIaqi(),pollutantsList);
                viewModel.initLocationInfo(getApplicationContext(),locationName,locationTime,data.getCity());
                viewModel.initAQIInfo(getApplicationContext(),aqiNumber,aqiDescription,data.getAqi());
                progressDialog.dismiss();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_AIR_QUALITY_ACTIVITY_UPDATE);
        registerReceiver(receiver,intentFilter);


        startServiceForAirQualityUpdateRequest(this);
        progressDialog = new ProgressDialogWrapper(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
