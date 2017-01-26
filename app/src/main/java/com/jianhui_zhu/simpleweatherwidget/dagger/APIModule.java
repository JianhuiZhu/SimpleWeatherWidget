package com.jianhui_zhu.simpleweatherwidget.dagger;

/**
 * Created by jianhuizhu on 2017-01-26.
 */
import android.util.Log;
import com.jianhui_zhu.simpleweatherwidget.BuildConfig;
import com.jianhui_zhu.simpleweatherwidget.ViewModelSimpleWeather;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.WeatherAPI;

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
    WeatherAPI provideWeatherAPI(Retrofit retrofit) {
        return retrofit.create(WeatherAPI.class);
    }

    @Provides
    Retrofit providesRetrofit() {
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
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    ViewModelSimpleWeather providesViewModelSimpleWeather() {
        return new ViewModelSimpleWeather();
    }
}

