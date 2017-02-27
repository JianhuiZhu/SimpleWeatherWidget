
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class So2 extends Pollutant implements Parcelable
{
    public static final Parcelable.Creator<So2> CREATOR = new Parcelable.Creator<So2>() {
        public So2 createFromParcel(Parcel in) {
            return new So2(in);
        }

        public So2[] newArray(int size) {
            return new So2[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private So2(Parcel in) {
        super(in);
    }
}
