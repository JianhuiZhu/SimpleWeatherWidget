
package com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Alert;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Daily;


public class DarkSkyDailyWeatherResponse implements Parcelable
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
    @SerializedName("daily")
    @Expose
    private Daily daily;
    @SerializedName("alerts")
    @Expose
    private List<Alert> alerts = new ArrayList<Alert>();
    public final static Parcelable.Creator<DarkSkyDailyWeatherResponse> CREATOR = new Creator<DarkSkyDailyWeatherResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DarkSkyDailyWeatherResponse createFromParcel(Parcel in) {
            DarkSkyDailyWeatherResponse instance = new DarkSkyDailyWeatherResponse();
            instance.latitude = ((double) in.readValue((double.class.getClassLoader())));
            instance.longitude = ((double) in.readValue((double.class.getClassLoader())));
            instance.timezone = ((String) in.readValue((String.class.getClassLoader())));
            instance.offset = ((int) in.readValue((int.class.getClassLoader())));
            instance.daily = ((Daily) in.readValue((Daily.class.getClassLoader())));
            in.readList(instance.alerts, (Alert.class.getClassLoader()));
            return instance;
        }

        public DarkSkyDailyWeatherResponse[] newArray(int size) {
            return (new DarkSkyDailyWeatherResponse[size]);
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
     *     The daily
     */
    public Daily getDaily() {
        return daily;
    }

    /**
     * 
     * @param daily
     *     The daily
     */
    public void setDaily(Daily daily) {
        this.daily = daily;
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
        dest.writeValue(daily);
        dest.writeList(alerts);
    }

    public int describeContents() {
        return  0;
    }

}
