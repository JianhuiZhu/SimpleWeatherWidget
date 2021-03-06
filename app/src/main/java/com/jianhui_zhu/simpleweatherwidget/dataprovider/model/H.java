
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class H implements Parcelable
{

    @SerializedName("v")
    @Expose
    private double v;
    public final static Parcelable.Creator<H> CREATOR = new Creator<H>() {


        @SuppressWarnings({
            "unchecked"
        })
        public H createFromParcel(Parcel in) {
            H instance = new H();
            instance.v = ((double) in.readValue((double.class.getClassLoader())));
            return instance;
        }

        public H[] newArray(int size) {
            return (new H[size]);
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
