package com.jianhui_zhu.simpleweatherwidget.dagger;

/**
 * Created by jianhuizhu on 2017-01-26.
 */
import android.util.Log;
import com.jianhui_zhu.simpleweatherwidget.BuildConfig;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.AirQualityAPI;
import com.jianhui_zhu.simpleweatherwidget.detailweather.ViewModelDetailActivity;
import com.jianhui_zhu.simpleweatherwidget.widget.ViewModelSimpleWeather;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherAPI;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherManager;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherManagerImpl;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by jianhuizhu on 2017-01-19.
 */
@Module
public class APIModule {
    @Provides
    @Inject
    public WeatherAPI provideWeatherAPI(Retrofit.Builder retrofitBuilder) {

        return retrofitBuilder
                .baseUrl(BuildConfig.DARK_SKY_URL)
                .build()
                .create(WeatherAPI.class);
    }

    @Provides
    @Inject
    public AirQualityAPI provideAirQualityAPI(Retrofit.Builder retrofitBuilder){
        return retrofitBuilder
                .baseUrl(BuildConfig.AIR_QUALITY_INDEX_URL)
                .build()
                .create(AirQualityAPI.class);
    }

    @Provides
    public Retrofit.Builder providesRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d("OKHttp", message);
                    }
                })
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(6, TimeUnit.SECONDS)
                .build();
        return new Retrofit
                .Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()));
    }


}

