
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model.uv;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class UVResponse implements Parcelable
{

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("data")
    @Expose
    private double data;
    public final static Parcelable.Creator<UVResponse> CREATOR = new Creator<UVResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UVResponse createFromParcel(Parcel in) {
            UVResponse instance = new UVResponse();
            instance.time = ((String) in.readValue((String.class.getClassLoader())));
            instance.location = ((Location) in.readValue((Location.class.getClassLoader())));
            instance.data = ((double) in.readValue((double.class.getClassLoader())));
            return instance;
        }

        public UVResponse[] newArray(int size) {
            return (new UVResponse[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The time
     */
    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The data
     */
    public double getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(double data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(time);
        dest.writeValue(location);
        dest.writeValue(data);
    }

    public int describeContents() {
        return  0;
    }

}
