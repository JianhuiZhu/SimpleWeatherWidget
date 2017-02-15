package com.jianhui_zhu.simpleweatherwidget.detailweather;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.Util;
import com.jianhui_zhu.simpleweatherwidget.dagger.APIModule;
import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerAPIComponent;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;

import java.util.Date;

import static com.jianhui_zhu.simpleweatherwidget.BroadcastIntentHandler.*;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jianhui_zhu.simpleweatherwidget.PermissionUtil.*;
import static com.jianhui_zhu.simpleweatherwidget.WeatherConstant.*;

public class DetailActivity extends AppCompatActivity implements WeatherForecastAdapter.AdapterPositionChangeListener {
    @Inject
    ViewModelDetailActivity viewModel;
    private long lastUpdate = 0;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ads_adview)
    AdView adView;
    @BindView(R.id.weather_forecast_recycler_view)
    com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager weatherForecastRecyclerView;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(isWeatherUpdateForActivity(intent)){
                lastUpdate = System.currentTimeMillis();
                viewModel.refreshWeatherForecast(weatherForecastRecyclerView,intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        JodaTimeAndroid.init(this);
        MobileAds.initialize(getApplicationContext(),getString(R.string.ads_unit));
        DaggerAPIComponent.builder().aPIModule(new APIModule()).build().inject(this);
        if(isAcquiringPermission(getIntent())) {
            getPermissionRequestDialog(this).show();
        }
        viewModel.init(this,toolbar,weatherForecastRecyclerView,adView,getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_ACTIVITY_UPDATE);
        registerReceiver(receiver,intentFilter);
        requestUpdateWhenExpired();
    }

    private void requestUpdateWhenExpired(){
        if((System.currentTimeMillis() - lastUpdate) > VALID_PERIOD){
            broadcastDetailWeatherUpdateRequest(this);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    broadcastBriefWeatherUpdateRequest(this);
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
    public void changeToolbarTitle(DateTime date) {
        toolbar.setTitle(Util.getWeekDay(this,date));
        toolbar.setSubtitle(Util.getDateWithProperFormat(this,date));
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
