
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pm10 extends Pollutant implements Parcelable
{
    public static final Parcelable.Creator<Pm10> CREATOR = new Parcelable.Creator<Pm10>() {
        public Pm10 createFromParcel(Parcel in) {
            return new Pm10(in);
        }

        public Pm10[] newArray(int size) {
            return new Pm10[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private Pm10(Parcel in) {
        super(in);
    }
}
