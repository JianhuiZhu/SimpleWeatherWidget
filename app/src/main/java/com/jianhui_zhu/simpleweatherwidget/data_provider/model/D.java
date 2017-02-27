
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class D extends Pollutant implements Parcelable
{
    public static final Parcelable.Creator<D> CREATOR = new Parcelable.Creator<D>() {
        public D createFromParcel(Parcel in) {
            return new D(in);
        }

        public D[] newArray(int size) {
            return new D[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private D(Parcel in) {
        super(in);
    }
}
