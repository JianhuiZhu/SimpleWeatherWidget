
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class No2 implements Parcelable
{

    @SerializedName("v")
    @Expose
    private int v;
    public final static Parcelable.Creator<No2> CREATOR = new Creator<No2>() {


        @SuppressWarnings({
            "unchecked"
        })
        public No2 createFromParcel(Parcel in) {
            No2 instance = new No2();
            instance.v = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public No2 [] newArray(int size) {
            return (new No2[size]);
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