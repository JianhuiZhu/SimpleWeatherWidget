
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
    public final static Parcelable.Creator<Iaqi> CREATOR = new Creator<Iaqi>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Iaqi createFromParcel(Parcel in) {
            Iaqi instance = new Iaqi();
            instance.co = ((Co) in.readValue((Co.class.getClassLoader())));
            instance.d = ((D) in.readValue((D.class.getClassLoader())));
            instance.h = ((H) in.readValue((H.class.getClassLoader())));
            instance.no2 = ((No2) in.readValue((No2.class.getClassLoader())));
            instance.o3 = ((O3) in.readValue((O3.class.getClassLoader())));
            instance.p = ((P) in.readValue((P.class.getClassLoader())));
            instance.pm10 = ((Pm10) in.readValue((Pm10.class.getClassLoader())));
            instance.pm25 = ((Pm25) in.readValue((Pm25.class.getClassLoader())));
            instance.so2 = ((So2) in.readValue((So2.class.getClassLoader())));
            instance.t = ((T) in.readValue((T.class.getClassLoader())));
            instance.w = ((W) in.readValue((W.class.getClassLoader())));
            instance.wd = ((Wd) in.readValue((Wd.class.getClassLoader())));
            return instance;
        }

        public Iaqi[] newArray(int size) {
            return (new Iaqi[size]);
        }

    }
    ;

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(co);
        dest.writeValue(d);
        dest.writeValue(h);
        dest.writeValue(no2);
        dest.writeValue(o3);
        dest.writeValue(p);
        dest.writeValue(pm10);
        dest.writeValue(pm25);
        dest.writeValue(so2);
        dest.writeValue(t);
        dest.writeValue(w);
        dest.writeValue(wd);
    }

    public int describeContents() {
        return  0;
    }

}
