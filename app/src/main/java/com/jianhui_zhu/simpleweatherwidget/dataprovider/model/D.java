
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class D implements Parcelable
{

    @SerializedName("v")
    @Expose
    private int v;
    public final static Parcelable.Creator<D> CREATOR = new Creator<D>() {


        @SuppressWarnings({
            "unchecked"
        })
        public D createFromParcel(Parcel in) {
            D instance = new D();
            instance.v = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public D[] newArray(int size) {
            return (new D[size]);
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
