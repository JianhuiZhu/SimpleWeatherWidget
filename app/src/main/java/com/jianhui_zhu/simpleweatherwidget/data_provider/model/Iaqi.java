
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Iaqi implements Parcelable
{

    @SerializedName("co")
    @Expose
    
    private Co co;
    @SerializedName("d")
    @Expose
    
    private D d;
    @SerializedName("h")
    @Expose
    
    private H h;
    @SerializedName("no2")
    @Expose
    
    private No2 no2;
    @SerializedName("o3")
    @Expose
    
    private O3 o3;
    @SerializedName("p")
    @Expose
    
    private P p;
    @SerializedName("pm10")
    @Expose
    
    private Pm10 pm10;
    @SerializedName("pm25")
    @Expose
    
    private Pm25 pm25;
    @SerializedName("so2")
    @Expose
    
    private So2 so2;
    @SerializedName("t")
    @Expose
    
    private T t;
    @SerializedName("w")
    @Expose
    
    private W w;
    @SerializedName("wd")
    @Expose
    
    private Wd wd;


    protected Iaqi(Parcel in) {
        co = in.readParcelable(Co.class.getClassLoader());
        d = in.readParcelable(D.class.getClassLoader());
        h = in.readParcelable(H.class.getClassLoader());
        no2 = in.readParcelable(No2.class.getClassLoader());
        o3 = in.readParcelable(O3.class.getClassLoader());
        p = in.readParcelable(P.class.getClassLoader());
        pm10 = in.readParcelable(Pm10.class.getClassLoader());
        pm25 = in.readParcelable(Pm25.class.getClassLoader());
        so2 = in.readParcelable(So2.class.getClassLoader());
        t = in.readParcelable(T.class.getClassLoader());
        w = in.readParcelable(W.class.getClassLoader());
        wd = in.readParcelable(Wd.class.getClassLoader());
    }

    public static final Creator<Iaqi> CREATOR = new Creator<Iaqi>() {
        @Override
        public Iaqi createFromParcel(Parcel in) {
            return new Iaqi(in);
        }

        @Override
        public Iaqi[] newArray(int size) {
            return new Iaqi[size];
        }
    };

    /**
     * 
     * @return
     *     The co
     */
    public Co getCo() {
        return co;
    }

    /**
     * 
     * @param co
     *     The co
     */
    public void setCo(Co co) {
        this.co = co;
    }

    /**
     * 
     * @return
     *     The d
     */
    public D getD() {
        return d;
    }

    /**
     * 
     * @param d
     *     The d
     */
    public void setD(D d) {
        this.d = d;
    }

    /**
     * 
     * @return
     *     The h
     */
    public H getH() {
        return h;
    }

    /**
     * 
     * @param h
     *     The h
     */
    public void setH(H h) {
        this.h = h;
    }

    /**
     * 
     * @return
     *     The no2
     */
    public No2 getNo2() {
        return no2;
    }

    /**
     * 
     * @param no2
     *     The no2
     */
    public void setNo2(No2 no2) {
        this.no2 = no2;
    }

    /**
     * 
     * @return
     *     The o3
     */
    public O3 getO3() {
        return o3;
    }

    /**
     * 
     * @param o3
     *     The o3
     */
    public void setO3(O3 o3) {
        this.o3 = o3;
    }

    /**
     * 
     * @return
     *     The p
     */
    public P getP() {
        return p;
    }

    /**
     * 
     * @param p
     *     The p
     */
    public void setP(P p) {
        this.p = p;
    }

    /**
     * 
     * @return
     *     The pm10
     */
    public Pm10 getPm10() {
        return pm10;
    }

    /**
     * 
     * @param pm10
     *     The pm10
     */
    public void setPm10(Pm10 pm10) {
        this.pm10 = pm10;
    }

    /**
     * 
     * @return
     *     The pm25
     */
    public Pm25 getPm25() {
        return pm25;
    }

    /**
     * 
     * @param pm25
     *     The pm25
     */
    public void setPm25(Pm25 pm25) {
        this.pm25 = pm25;
    }

    /**
     * 
     * @return
     *     The so2
     */
    public So2 getSo2() {
        return so2;
    }

    /**
     * 
     * @param so2
     *     The so2
     */
    public void setSo2(So2 so2) {
        this.so2 = so2;
    }

    /**
     * 
     * @return
     *     The t
     */
    public T getT() {
        return t;
    }

    /**
     * 
     * @param t
     *     The t
     */
    public void setT(T t) {
        this.t = t;
    }

    /**
     * 
     * @return
     *     The w
     */
    public W getW() {
        return w;
    }

    /**
     * 
     * @param w
     *     The w
     */
    public void setW(W w) {
        this.w = w;
    }

    /**
     * 
     * @return
     *     The wd
     */
    public Wd getWd() {
        return wd;
    }

    /**
     * 
     * @param wd
     *     The wd
     */
    public void setWd(Wd wd) {
        this.wd = wd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(co, flags);
        dest.writeParcelable(d, flags);
        dest.writeParcelable(h, flags);
        dest.writeParcelable(no2, flags);
        dest.writeParcelable(o3, flags);
        dest.writeParcelable(p, flags);
        dest.writeParcelable(pm10, flags);
        dest.writeParcelable(pm25, flags);
        dest.writeParcelable(so2, flags);
        dest.writeParcelable(t, flags);
        dest.writeParcelable(w, flags);
        dest.writeParcelable(wd, flags);
    }
}
