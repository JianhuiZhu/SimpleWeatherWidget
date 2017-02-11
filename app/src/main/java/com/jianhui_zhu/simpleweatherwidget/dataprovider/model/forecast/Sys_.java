
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Sys_ implements Parcelable {

    @SerializedName("pod")
    @Expose
    private String pod;

    protected Sys_(Parcel in) {
        pod = in.readString();
    }

    public static final Creator<Sys_> CREATOR = new Creator<Sys_>() {
        @Override
        public Sys_ createFromParcel(Parcel in) {
            return new Sys_(in);
        }

        @Override
        public Sys_[] newArray(int size) {
            return new Sys_[size];
        }
    };

    /**
     * 
     * @return
     *     The pod
     */
    public String getPod() {
        return pod;
    }

    /**
     * 
     * @param pod
     *     The pod
     */
    public void setPod(String pod) {
        this.pod = pod;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pod);
    }
}
