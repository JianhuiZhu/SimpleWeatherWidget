
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model.current;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Clouds;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Coord;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Main;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Weather;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Wind;


public class CurrentWeatherResponse implements Parcelable{

    @SerializedName("coord")
    @Expose

    private Coord coord;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = new ArrayList<Weather>();
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose

    private Main main;
    @SerializedName("wind")
    @Expose

    private Wind wind;
    @SerializedName("clouds")
    @Expose

    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private long dt;
    @SerializedName("sys")
    @Expose

    private Sys sys;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private int cod;

    protected CurrentWeatherResponse(Parcel in) {
        coord = in.readParcelable(Coord.class.getClassLoader());
        weather = in.createTypedArrayList(Weather.CREATOR);
        base = in.readString();
        main = in.readParcelable(Main.class.getClassLoader());
        wind = in.readParcelable(Wind.class.getClassLoader());
        clouds = in.readParcelable(Clouds.class.getClassLoader());
        dt = in.readLong();
        sys = in.readParcelable(Sys.class.getClassLoader());
        id = in.readInt();
        name = in.readString();
        cod = in.readInt();
    }

    public static final Creator<CurrentWeatherResponse> CREATOR = new Creator<CurrentWeatherResponse>() {
        @Override
        public CurrentWeatherResponse createFromParcel(Parcel in) {
            return new CurrentWeatherResponse(in);
        }

        @Override
        public CurrentWeatherResponse[] newArray(int size) {
            return new CurrentWeatherResponse[size];
        }
    };

    /**
     * 
     * @return
     *     The coord
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * 
     * @param coord
     *     The coord
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /**
     * 
     * @return
     *     The weather
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * 
     * @param weather
     *     The weather
     */
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * 
     * @return
     *     The base
     */
    public String getBase() {
        return base;
    }

    /**
     * 
     * @param base
     *     The base
     */
    public void setBase(String base) {
        this.base = base;
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
     *     The sys
     */
    public Sys getSys() {
        return sys;
    }

    /**
     * 
     * @param sys
     *     The sys
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * 
     * @param cod
     *     The cod
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(coord, flags);
        dest.writeTypedList(weather);
        dest.writeString(base);
        dest.writeParcelable(main, flags);
        dest.writeParcelable(wind, flags);
        dest.writeParcelable(clouds, flags);
        dest.writeLong(dt);
        dest.writeParcelable(sys, flags);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(cod);
    }
}
