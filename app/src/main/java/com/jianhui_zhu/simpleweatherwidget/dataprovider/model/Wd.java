
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wd implements Parcelable
{

    @SerializedName("v")
    @Expose
    private int v;
    public final static Parcelable.Creator<Wd> CREATOR = new Creator<Wd>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Wd createFromParcel(Parcel in) {
            Wd instance = new Wd();
            instance.v = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public Wd[] newArray(int size) {
            return (new Wd[size]);
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
