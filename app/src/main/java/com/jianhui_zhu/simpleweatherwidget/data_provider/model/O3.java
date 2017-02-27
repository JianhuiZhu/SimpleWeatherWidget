
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class O3 extends Pollutant implements Parcelable
{
    public static final Parcelable.Creator<O3> CREATOR = new Parcelable.Creator<O3>() {
        public O3 createFromParcel(Parcel in) {
            return new O3(in);
        }

        public O3[] newArray(int size) {
            return new O3[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private O3(Parcel in) {
        super(in);
    }
}
