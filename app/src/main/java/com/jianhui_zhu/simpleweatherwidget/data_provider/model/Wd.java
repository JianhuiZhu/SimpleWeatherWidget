
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wd extends Pollutant implements Parcelable
{
    public static final Parcelable.Creator<Wd> CREATOR = new Parcelable.Creator<Wd>() {
        public Wd createFromParcel(Parcel in) {
            return new Wd(in);
        }

        public Wd[] newArray(int size) {
            return new Wd[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private Wd(Parcel in) {
        super(in);
    }
}
