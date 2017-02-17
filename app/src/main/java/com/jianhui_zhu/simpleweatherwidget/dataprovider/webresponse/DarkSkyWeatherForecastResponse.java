
package com.jianhui_zhu.simpleweatherwidget.dataprovider.webresponse;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Alert;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Currently;
import com.jianhui_zhu.simpleweatherwidget.dataprovider.model.Daily;


public class DarkSkyWeatherForecastResponse implements Parcelable
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
    @SerializedName("currently")
    @Expose
    private Currently currently;
    @SerializedName("alerts")
    @Expose
    private ArrayList<Alert> alerts = new ArrayList<Alert>();


    protected DarkSkyWeatherForecastResponse(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        timezone = in.readString();
        offset = in.readInt();
        daily = in.readParcelable(Daily.class.getClassLoader());
        currently = in.readParcelable(Currently.class.getClassLoader());
        alerts = in.createTypedArrayList(Alert.CREATOR);
    }

    public static final Creator<DarkSkyWeatherForecastResponse> CREATOR = new Creator<DarkSkyWeatherForecastResponse>() {
        @Override
        public DarkSkyWeatherForecastResponse createFromParcel(Parcel in) {
            return new DarkSkyWeatherForecastResponse(in);
        }

        @Override
        public DarkSkyWeatherForecastResponse[] newArray(int size) {
            return new DarkSkyWeatherForecastResponse[size];
        }
    };

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
    public ArrayList<Alert> getAlerts() {
        return alerts;
    }

    /**
     * 
     * @param alerts
     *     The alerts
     */
    public void setAlerts(ArrayList<Alert> alerts) {
        this.alerts = alerts;
    }

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(timezone);
        dest.writeInt(offset);
        dest.writeParcelable(daily, flags);
        dest.writeParcelable(currently, flags);
        dest.writeTypedList(alerts);
    }
}
