package com.jianhui_zhu.simpleweatherwidget.daily_weather;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AddressResult;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.DailyDataPoint;
import com.jianhui_zhu.simpleweatherwidget.utils.ProgressDialogWrapper;

import net.danlew.android.joda.JodaTimeAndroid;

import static com.jianhui_zhu.simpleweatherwidget.utils.BroadcastIntentHandler.*;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jianhui_zhu.simpleweatherwidget.utils.PermissionUtil.*;
import static com.jianhui_zhu.simpleweatherwidget.utils.Constant.*;

public class DailyWeatherActivity extends AppCompatActivity{
    ViewModelDailyWeatherActivity viewModel;
    private long lastUpdate = 0;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ads_adview)
    AdView adView;
    @BindView(R.id.weather_forecast_recycler_view)
    RecyclerView weatherForecastRecyclerView;
    @BindView(R.id.activity_detail)
    RelativeLayout layout;
    @BindView(R.id.today_weather_icon_image_view)
    ImageView todayWeatherIcon;
    @BindView(R.id.today_temperature_text_view)
    TextView todayTemperature;
    @BindView(R.id.temperature_max_text_view)
    TextView maxTemperature;
    @BindView(R.id.temperature_min_text_view)
    TextView minTemperature;
    @BindView(R.id.today_weather_card_view)
    CardView todayWeatherCard;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(isWeatherUpdateForActivity(intent)){
                lastUpdate = System.currentTimeMillis();

                viewModel.refreshWeatherForecast(weatherForecastRecyclerView,intent,getApplicationContext());

                DailyDataPoint todayWeatherData = intent.getParcelableExtra(TODAY_WEATHER);
                viewModel.updateTodayCardViewWithData(getApplicationContext(),todayWeatherCard,todayTemperature,maxTemperature,minTemperature,todayWeatherIcon,todayWeatherData);

                AddressResult addressResult = intent.getParcelableExtra(ADDRESS);
                viewModel.updateToolbarWithData(toolbar,addressResult);

                progressDialog.dismiss();
            }
        }
    };
    ProgressDialogWrapper progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        JodaTimeAndroid.init(this);
        MobileAds.initialize(getApplicationContext(),getString(R.string.ads_unit));

        if(isAcquiringPermission(getIntent())) {
            getPermissionRequestDialog(this).show();
        }
        viewModel = new ViewModelDailyWeatherActivity();
        viewModel.initToolbar(this,toolbar);
        viewModel.initRecyclerView(this,weatherForecastRecyclerView);
        viewModel.initAdView(adView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_WEATHER_ACTIVITY_UPDATE);
        registerReceiver(receiver,intentFilter);


        progressDialog = new ProgressDialogWrapper(this);
        startServiceForDetailWeatherUpdateRequest(this);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startServiceForBriefWeatherUpdateRequest(this);
                    this.finish();
                }else{
                    Toast.makeText(this,"Permission declined",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
