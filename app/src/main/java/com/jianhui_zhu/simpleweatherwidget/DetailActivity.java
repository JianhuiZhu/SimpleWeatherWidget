package com.jianhui_zhu.simpleweatherwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_header_textview)
    TextView toolbarHeaderTextView;
    @BindView(R.id.weather_forecast_recycler_view)
    RecyclerView weatherForecastRecyclerView;
    ViewModelSimpleWeather viewModel = new ViewModelSimpleWeather();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        viewModel.refreshCurrentLocationWeather(this,weatherForecastRecyclerView,null,null,null);
    }


}
