
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Viewport implements Parcelable
{

    @SerializedName("northeast")
    @Expose

    private NortheastViewport northeast;
    @SerializedName("southwest")
    @Expose
    private SouthwestViewport southwest;
    public final static Parcelable.Creator<Viewport> CREATOR = new Creator<Viewport>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Viewport createFromParcel(Parcel in) {
            Viewport instance = new Viewport();
            instance.northeast = ((NortheastViewport) in.readValue((NortheastViewport.class.getClassLoader())));
            instance.southwest = ((SouthwestViewport) in.readValue((SouthwestViewport.class.getClassLoader())));
            return instance;
        }

        public Viewport[] newArray(int size) {
            return (new Viewport[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The northeast
     */
    public NortheastViewport getNortheast() {
        return northeast;
    }

    /**
     * 
     * @param northeast
     *     The northeast
     */
    public void setNortheast(NortheastViewport northeast) {
        this.northeast = northeast;
    }

    /**
     * 
     * @return
     *     The southwest
     */
    public SouthwestViewport getSouthwest() {
        return southwest;
    }

    /**
     * 
     * @param southwest
     *     The southwest
     */
    public void setSouthwest(SouthwestViewport southwest) {
        this.southwest = southwest;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(northeast);
        dest.writeValue(southwest);
    }

    public int describeContents() {
        return  0;
    }

}
