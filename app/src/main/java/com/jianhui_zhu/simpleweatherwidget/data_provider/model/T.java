
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class T extends Pollutant implements Parcelable
{
    public static final Parcelable.Creator<T> CREATOR = new Parcelable.Creator<T>() {
        public T createFromParcel(Parcel in) {
            return new T(in);
        }

        public T[] newArray(int size) {
            return new T[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private T(Parcel in) {
        super(in);
    }
}
