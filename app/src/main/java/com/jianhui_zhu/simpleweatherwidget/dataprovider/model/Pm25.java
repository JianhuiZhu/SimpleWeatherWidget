
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pm25 implements Parcelable
{

    @SerializedName("v")
    @Expose
    private double v;
    public final static Parcelable.Creator<Pm25> CREATOR = new Creator<Pm25>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Pm25 createFromParcel(Parcel in) {
            Pm25 instance = new Pm25();
            instance.v = ((double) in.readValue((double.class.getClassLoader())));
            return instance;
        }

        public Pm25 [] newArray(int size) {
            return (new Pm25[size]);
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
