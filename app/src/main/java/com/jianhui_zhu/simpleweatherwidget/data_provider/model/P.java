
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class P extends Pollutant implements Parcelable
{
    public static final Parcelable.Creator<P> CREATOR = new Parcelable.Creator<P>() {
        public P createFromParcel(Parcel in) {
            return new P(in);
        }

        public P[] newArray(int size) {
            return new P[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private P(Parcel in) {
        super(in);
    }
}
