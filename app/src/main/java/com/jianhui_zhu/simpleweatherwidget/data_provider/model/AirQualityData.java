
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirQualityData implements Parcelable
{

    @SerializedName("aqi")
    @Expose
    private int aqi;
    @SerializedName("idx")
    @Expose
    private int idx;
    @SerializedName("attributions")
    @Expose
    private List<Attribution> attributions = new ArrayList<Attribution>();
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("dominentpol")
    @Expose
    private String dominentpol;
    @SerializedName("iaqi")
    @Expose
    private Iaqi iaqi;
    @SerializedName("time")
    @Expose
    private Time time;


    protected AirQualityData(Parcel in) {
        aqi = in.readInt();
        idx = in.readInt();
        attributions = in.createTypedArrayList(Attribution.CREATOR);
        city = in.readParcelable(City.class.getClassLoader());
        dominentpol = in.readString();
        iaqi = in.readParcelable(Iaqi.class.getClassLoader());
        time = in.readParcelable(Time.class.getClassLoader());
    }

    public static final Creator<AirQualityData> CREATOR = new Creator<AirQualityData>() {
        @Override
        public AirQualityData createFromParcel(Parcel in) {
            return new AirQualityData(in);
        }

        @Override
        public AirQualityData[] newArray(int size) {
            return new AirQualityData[size];
        }
    };

    /**
     * 
     * @return
     *     The aqi
     */
    public int getAqi() {
        return aqi;
    }

    /**
     * 
     * @param aqi
     *     The aqi
     */
    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    /**
     * 
     * @return
     *     The idx
     */
    public int getIdx() {
        return idx;
    }

    /**
     * 
     * @param idx
     *     The idx
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }

    /**
     * 
     * @return
     *     The attributions
     */
    public List<Attribution> getAttributions() {
        return attributions;
    }

    /**
     * 
     * @param attributions
     *     The attributions
     */
    public void setAttributions(List<Attribution> attributions) {
        this.attributions = attributions;
    }

    /**
     * 
     * @return
     *     The city
     */
    public City getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The dominentpol
     */
    public String getDominentpol() {
        return dominentpol;
    }

    /**
     * 
     * @param dominentpol
     *     The dominentpol
     */
    public void setDominentpol(String dominentpol) {
        this.dominentpol = dominentpol;
    }

    /**
     * 
     * @return
     *     The iaqi
     */
    public Iaqi getIaqi() {
        return iaqi;
    }

    /**
     * 
     * @param iaqi
     *     The iaqi
     */
    public void setIaqi(Iaqi iaqi) {
        this.iaqi = iaqi;
    }

    /**
     * 
     * @return
     *     The time
     */
    public Time getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(Time time) {
        this.time = time;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(aqi);
        dest.writeInt(idx);
        dest.writeTypedList(attributions);
        dest.writeParcelable(city, flags);
        dest.writeString(dominentpol);
        dest.writeParcelable(iaqi, flags);
        dest.writeParcelable(time, flags);
    }
}
