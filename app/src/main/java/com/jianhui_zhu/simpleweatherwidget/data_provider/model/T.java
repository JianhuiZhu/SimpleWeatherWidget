
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class T extends Pollutant implements Parcelable
{
    protected T(Parcel in) {
        super(in);
    }
}
