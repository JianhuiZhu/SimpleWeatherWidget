
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pm10 implements Parcelable
{

    @SerializedName("v")
    @Expose
    private int v;
    public final static Parcelable.Creator<Pm10> CREATOR = new Creator<Pm10>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Pm10 createFromParcel(Parcel in) {
            Pm10 instance = new Pm10();
            instance.v = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public Pm10 [] newArray(int size) {
            return (new Pm10[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The v
     */
    public int getV() {
        return v;
    }

    /**
     * 
     * @param v
     *     The v
     */
    public void setV(int v) {
        this.v = v;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(v);
    }

    public int describeContents() {
        return  0;
    }

}
