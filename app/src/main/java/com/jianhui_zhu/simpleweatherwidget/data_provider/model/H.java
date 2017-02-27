
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class H extends Pollutant implements Parcelable
{
    public static final Parcelable.Creator<H> CREATOR = new Parcelable.Creator<H>() {
        public H createFromParcel(Parcel in) {
            return new H(in);
        }

        public H[] newArray(int size) {
            return new H[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private H(Parcel in) {
        super(in);
    }
}
