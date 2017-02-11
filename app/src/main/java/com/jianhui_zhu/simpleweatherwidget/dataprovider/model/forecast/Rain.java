
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Rain implements Parcelable {
    @SerializedName("3h")
    @Expose
    String threeHourRainVolume;

    protected Rain(Parcel in) {
        threeHourRainVolume = in.readString();
    }

    public static final Creator<Rain> CREATOR = new Creator<Rain>() {
        @Override
        public Rain createFromParcel(Parcel in) {
            return new Rain(in);
        }

        @Override
        public Rain[] newArray(int size) {
            return new Rain[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(threeHourRainVolume);
    }
}
