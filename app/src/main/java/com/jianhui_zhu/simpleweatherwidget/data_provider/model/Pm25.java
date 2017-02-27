
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pm25 extends Pollutant implements Parcelable
{
    public static final Parcelable.Creator<Pm25> CREATOR = new Parcelable.Creator<Pm25>() {
        public Pm25 createFromParcel(Parcel in) {
            return new Pm25(in);
        }

        public Pm25[] newArray(int size) {
            return new Pm25[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private Pm25(Parcel in) {
        super(in);
    }
}
