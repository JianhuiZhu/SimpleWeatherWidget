
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
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
    public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            Data instance = new Data();
            instance.aqi = ((int) in.readValue((int.class.getClassLoader())));
            instance.idx = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.attributions, (com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Attribution.class.getClassLoader()));
            instance.city = ((City) in.readValue((City.class.getClassLoader())));
            instance.dominentpol = ((String) in.readValue((String.class.getClassLoader())));
            instance.iaqi = ((Iaqi) in.readValue((Iaqi.class.getClassLoader())));
            instance.time = ((Time) in.readValue((Time.class.getClassLoader())));
            return instance;
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(aqi);
        dest.writeValue(idx);
        dest.writeList(attributions);
        dest.writeValue(city);
        dest.writeValue(dominentpol);
        dest.writeValue(iaqi);
        dest.writeValue(time);
    }

    public int describeContents() {
        return  0;
    }

}
