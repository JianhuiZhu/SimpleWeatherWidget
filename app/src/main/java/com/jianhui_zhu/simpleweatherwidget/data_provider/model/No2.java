
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class No2 extends Pollutant implements Parcelable
{
    public static final Parcelable.Creator<No2> CREATOR = new Parcelable.Creator<No2>() {
        public No2 createFromParcel(Parcel in) {
            return new No2(in);
        }

        public No2[] newArray(int size) {
            return new No2[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private No2(Parcel in) {
        super(in);
    }
}
