
package com.jianhui_zhu.simpleweatherwidget.dataprovider.model.forecast;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Clouds;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Main;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Weather;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.common.Wind;


public class List {

    @SerializedName("dt")
    @Expose
    private int dt;
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

    /**
     * 
     * @return
     *     The dt
     */
    public int getDt() {
        return dt;
    }

    /**
     * 
     * @param dt
     *     The dt
     */
    public void setDt(int dt) {
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

}
