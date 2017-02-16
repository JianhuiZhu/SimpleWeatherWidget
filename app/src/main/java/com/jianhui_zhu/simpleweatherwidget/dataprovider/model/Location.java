
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Location implements Parcelable
{

    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    public final static Parcelable.Creator<Location> CREATOR = new Creator<Location>() {


        public Location createFromParcel(Parcel in) {
            Location instance = new Location();
            instance.latitude = ((double) in.readValue((double.class.getClassLoader())));
            instance.longitude = ((double) in.readValue((double.class.getClassLoader())));
            return instance;
        }

        public Location[] newArray(int size) {
            return (new Location[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(latitude);
        dest.writeValue(longitude);
    }

    public int describeContents() {
        return  0;
    }

}
