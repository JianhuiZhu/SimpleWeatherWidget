
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Co extends Pollutant implements Parcelable
{

    public static final Parcelable.Creator<Co> CREATOR = new Parcelable.Creator<Co>() {
        public Co createFromParcel(Parcel in) {
            return new Co(in);
        }

        public Co[] newArray(int size) {
            return new Co[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }

    private Co(Parcel in) {
        super(in);
    }
}
