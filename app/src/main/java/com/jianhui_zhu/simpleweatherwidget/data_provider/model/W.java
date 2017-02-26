
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class W extends Pollutant implements Parcelable
{
    protected W(Parcel in) {
        super(in);
    }
}
