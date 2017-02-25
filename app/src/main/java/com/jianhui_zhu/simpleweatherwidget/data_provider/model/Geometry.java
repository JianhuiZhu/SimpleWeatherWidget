
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Geometry implements Parcelable
{

    @SerializedName("bounds")
    @Expose

    private Bounds bounds;
    @SerializedName("location")
    @Expose

    private Location location;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("viewport")
    @Expose

    private Viewport viewport;
    public final static Parcelable.Creator<Geometry> CREATOR = new Creator<Geometry>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Geometry createFromParcel(Parcel in) {
            Geometry instance = new Geometry();
            instance.bounds = ((Bounds) in.readValue((Bounds.class.getClassLoader())));
            instance.location = ((Location) in.readValue((Location.class.getClassLoader())));
            instance.locationType = ((String) in.readValue((String.class.getClassLoader())));
            instance.viewport = ((Viewport) in.readValue((Viewport.class.getClassLoader())));
            return instance;
        }

        public Geometry[] newArray(int size) {
            return (new Geometry[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The bounds
     */
    public Bounds getBounds() {
        return bounds;
    }

    /**
     * 
     * @param bounds
     *     The bounds
     */
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
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
     *     The locationType
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * 
     * @param locationType
     *     The location_type
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /**
     * 
     * @return
     *     The viewport
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     * 
     * @param viewport
     *     The viewport
     */
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(bounds);
        dest.writeValue(location);
        dest.writeValue(locationType);
        dest.writeValue(viewport);
    }

    public int describeContents() {
        return  0;
    }

}
