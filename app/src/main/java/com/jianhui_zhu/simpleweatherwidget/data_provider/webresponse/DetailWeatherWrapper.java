package com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AddressResult;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.Daily;

/**
 * Created by jianhuizhu on 2017-03-07.
 */

public class DetailWeatherWrapper implements Parcelable{
    private final Daily daily;
    private final AddressResult addressResult;
    public DetailWeatherWrapper(Daily daily, AddressResult addressResult){
        this.daily = daily;
        this.addressResult = addressResult;
    }

    protected DetailWeatherWrapper(Parcel in) {
        daily = in.readParcelable(Daily.class.getClassLoader());
        addressResult = in.readParcelable(AddressResult.class.getClassLoader());
    }

    public static final Creator<DetailWeatherWrapper> CREATOR = new Creator<DetailWeatherWrapper>() {
        @Override
        public DetailWeatherWrapper createFromParcel(Parcel in) {
            return new DetailWeatherWrapper(in);
        }

        @Override
        public DetailWeatherWrapper[] newArray(int size) {
            return new DetailWeatherWrapper[size];
        }
    };

    public Daily getDaily() {
        return daily;
    }

    public AddressResult getAddressResult() {
        return addressResult;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(daily, flags);
        dest.writeParcelable(addressResult, flags);
    }
}
