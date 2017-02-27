
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class W extends Pollutant implements Parcelable
{
    public static final Parcelable.Creator<W> CREATOR = new Parcelable.Creator<W>() {
        public W createFromParcel(Parcel in) {
            return new W(in);
        }

        public W[] newArray(int size) {
            return new W[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private W(Parcel in) {
        super(in);
    }
}
