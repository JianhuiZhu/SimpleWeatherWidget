
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class D extends Pollutant implements Parcelable
{
    protected D(Parcel in) {
        super(in);
    }
}
