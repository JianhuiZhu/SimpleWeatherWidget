
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time implements Parcelable
{

    @SerializedName("s")
    @Expose
    private String s;
    @SerializedName("tz")
    @Expose
    private String tz;
    @SerializedName("v")
    @Expose
    private double v;
    public final static Parcelable.Creator<Time> CREATOR = new Creator<Time>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Time createFromParcel(Parcel in) {
            Time instance = new Time();
            instance.s = ((String) in.readValue((String.class.getClassLoader())));
            instance.tz = ((String) in.readValue((String.class.getClassLoader())));
            instance.v = ((double) in.readValue((double.class.getClassLoader())));
            return instance;
        }

        public Time[] newArray(int size) {
            return (new Time[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The s
     */
    public String getS() {
        return s;
    }

    /**
     * 
     * @param s
     *     The s
     */
    public void setS(String s) {
        this.s = s;
    }

    /**
     * 
     * @return
     *     The tz
     */
    public String getTz() {
        return tz;
    }

    /**
     * 
     * @param tz
     *     The tz
     */
    public void setTz(String tz) {
        this.tz = tz;
    }

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
        dest.writeValue(s);
        dest.writeValue(tz);
        dest.writeValue(v);
    }

    public int describeContents() {
        return  0;
    }

}
