
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model.forecast;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Clouds;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Main;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Weather;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Wind;


public class List implements Parcelable {

    @SerializedName("dt")
    @Expose
    private long dt;
    @SerializedName("main")
    @Expose

    private Main main;
    @SerializedName("weather")
    @Expose

    private java.util.List<Weather> weather = new ArrayList<>();
    @SerializedName("clouds")
    @Expose

    private Clouds clouds;
    @SerializedName("wind")
    @Expose

    private Wind wind;
    @SerializedName("rain")
    @Expose

    private Rain rain;
    @SerializedName("sys")
    @Expose

    private Sys_ sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;


    protected List(Parcel in) {
        dt = in.readLong();
        main = in.readParcelable(Main.class.getClassLoader());
        weather = in.createTypedArrayList(Weather.CREATOR);
        clouds = in.readParcelable(Clouds.class.getClassLoader());
        wind = in.readParcelable(Wind.class.getClassLoader());
        rain = in.readParcelable(Rain.class.getClassLoader());
        sys = in.readParcelable(Sys_.class.getClassLoader());
        dtTxt = in.readString();
    }

    public static final Creator<List> CREATOR = new Creator<List>() {
        @Override
        public List createFromParcel(Parcel in) {
            return new List(in);
        }

        @Override
        public List[] newArray(int size) {
            return new List[size];
        }
    };

    /**
     * 
     * @return
     *     The dt
     */
    public long getDt() {
        return dt;
    }

    /**
     * 
     * @param dt
     *     The dt
     */
    public void setDt(long dt) {
        this.dt = dt;
    }

    /**
     * 
     * @return
     *     The main
     */
    public Main getMain() {
        return main;
    }

    /**
     * 
     * @param main
     *     The main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The weather
     */
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    /**
     * 
     * @param weather
     *     The weather
     */
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * 
     * @return
     *     The clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * 
     * @param clouds
     *     The clouds
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * 
     * @return
     *     The wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * 
     * @param wind
     *     The wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * 
     * @return
     *     The rain
     */
    public Rain getRain() {
        return rain;
    }

    /**
     * 
     * @param rain
     *     The rain
     */
    public void setRain(Rain rain) {
        this.rain = rain;
    }

    /**
     * 
     * @return
     *     The sys
     */
    public Sys_ getSys() {
        return sys;
    }

    /**
     * 
     * @param sys
     *     The sys
     */
    public void setSys(Sys_ sys) {
        this.sys = sys;
    }

    /**
     * 
     * @return
     *     The dtTxt
     */
    public String getDtTxt() {
        return dtTxt;
    }

    /**
     * 
     * @param dtTxt
     *     The dt_txt
     */
    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(dt);
        dest.writeParcelable(main, flags);
        dest.writeTypedList(weather);
        dest.writeParcelable(clouds, flags);
        dest.writeParcelable(wind, flags);
        dest.writeParcelable(rain, flags);
        dest.writeParcelable(sys, flags);
        dest.writeString(dtTxt);
    }
}
