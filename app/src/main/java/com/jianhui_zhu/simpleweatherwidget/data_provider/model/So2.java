
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class So2 extends Pollutant implements Parcelable
{
    protected So2(Parcel in) {
        super(in);
    }
}
