
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pm10 extends Pollutant implements Parcelable
{
    protected Pm10(Parcel in) {
        super(in);
    }
}
