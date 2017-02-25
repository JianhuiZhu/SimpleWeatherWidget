
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class So2 implements Parcelable
{

    @SerializedName("v")
    @Expose
    private double v;
    public final static Parcelable.Creator<So2> CREATOR = new Creator<So2>() {


        @SuppressWarnings({
            "unchecked"
        })
        public So2 createFromParcel(Parcel in) {
            So2 instance = new So2();
            instance.v = ((double) in.readValue((double.class.getClassLoader())));
            return instance;
        }

        public So2 [] newArray(int size) {
            return (new So2[size]);
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
