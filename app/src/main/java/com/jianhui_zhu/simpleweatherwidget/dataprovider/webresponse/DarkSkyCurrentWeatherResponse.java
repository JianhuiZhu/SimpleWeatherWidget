
package com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Alert;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Currently;

@Generated("org.jsonschema2pojo")
public class DarkSkyCurrentWeatherResponse implements Parcelable
{

    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("offset")
    @Expose
    private int offset;
    @SerializedName("currently")
    @Expose
    private Currently currently;
    @SerializedName("alerts")
    @Expose
    private List<Alert> alerts = new ArrayList<Alert>();
    public final static Parcelable.Creator<DarkSkyCurrentWeatherResponse> CREATOR = new Creator<DarkSkyCurrentWeatherResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DarkSkyCurrentWeatherResponse createFromParcel(Parcel in) {
            DarkSkyCurrentWeatherResponse instance = new DarkSkyCurrentWeatherResponse();
            instance.latitude = ((double) in.readValue((double.class.getClassLoader())));
            instance.longitude = ((double) in.readValue((double.class.getClassLoader())));
            instance.timezone = ((String) in.readValue((String.class.getClassLoader())));
            instance.offset = ((int) in.readValue((int.class.getClassLoader())));
            instance.currently = ((Currently) in.readValue((Currently.class.getClassLoader())));
            in.readList(instance.alerts, (Alert.class.getClassLoader()));
            return instance;
        }

        public DarkSkyCurrentWeatherResponse[] newArray(int size) {
            return (new DarkSkyCurrentWeatherResponse[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * 
     * @param timezone
     *     The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * 
     * @return
     *     The offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * 
     * @param offset
     *     The offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * 
     * @return
     *     The currently
     */
    public Currently getCurrently() {
        return currently;
    }

    /**
     * 
     * @param currently
     *     The currently
     */
    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    /**
     * 
     * @return
     *     The alerts
     */
    public List<Alert> getAlerts() {
        return alerts;
    }

    /**
     * 
     * @param alerts
     *     The alerts
     */
    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(timezone);
        dest.writeValue(offset);
        dest.writeValue(currently);
        dest.writeList(alerts);
    }

    public int describeContents() {
        return  0;
    }

}
