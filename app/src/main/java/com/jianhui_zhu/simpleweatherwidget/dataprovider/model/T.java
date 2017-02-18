
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class T implements Parcelable
{

    @SerializedName("v")
    @Expose
    private double v;
    public final static Parcelable.Creator<T> CREATOR = new Creator<T>() {


        @SuppressWarnings({
            "unchecked"
        })
        public T createFromParcel(Parcel in) {
            T instance = new T();
            instance.v = ((double) in.readValue((double.class.getClassLoader())));
            return instance;
        }

        public T[] newArray(int size) {
            return (new T[size]);
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
