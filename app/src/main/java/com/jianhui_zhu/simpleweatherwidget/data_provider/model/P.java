
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class P implements Parcelable
{

    @SerializedName("v")
    @Expose
    private double v;
    public final static Parcelable.Creator<P> CREATOR = new Creator<P>() {


        @SuppressWarnings({
            "unchecked"
        })
        public P createFromParcel(Parcel in) {
            P instance = new P();
            instance.v = ((double) in.readValue((double.class.getClassLoader())));
            return instance;
        }

        public P[] newArray(int size) {
            return (new P[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The v
     */
    public double getV() {
        return v;
    }

    /**
     * 
     * @param v
     *     The v
     */
    public void setV(double v) {
        this.v = v;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(v);
    }

    public int describeContents() {
        return  0;
    }

}
