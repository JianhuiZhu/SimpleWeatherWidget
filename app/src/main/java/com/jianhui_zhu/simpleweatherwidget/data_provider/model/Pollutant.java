package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jianhuizhu on 2017-02-25.
 */

public class Pollutant implements Parcelable{
    @SerializedName("v")
    @Expose
    private double v;

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    protected Pollutant(Parcel in) {
        v = in.readDouble();
    }

    public static final Creator<Pollutant> CREATOR = new Creator<Pollutant>() {
        @Override
        public Pollutant createFromParcel(Parcel in) {
            return new Pollutant(in);
        }

        @Override
        public Pollutant[] newArray(int size) {
            return new Pollutant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(v);
    }
}
