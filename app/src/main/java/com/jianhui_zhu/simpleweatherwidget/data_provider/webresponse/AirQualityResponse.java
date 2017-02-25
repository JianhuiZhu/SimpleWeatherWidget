
package com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AirQualityData;

public class AirQualityResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private AirQualityData airQualityData;
    public final static Parcelable.Creator<AirQualityResponse> CREATOR = new Creator<AirQualityResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AirQualityResponse createFromParcel(Parcel in) {
            AirQualityResponse instance = new AirQualityResponse();
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            instance.airQualityData = ((AirQualityData) in.readValue((AirQualityData.class.getClassLoader())));
            return instance;
        }

        public AirQualityResponse[] newArray(int size) {
            return (new AirQualityResponse[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The airQualityData
     */
    public AirQualityData getAirQualityData() {
        return airQualityData;
    }

    /**
     * 
     * @param airQualityData
     *     The airQualityData
     */
    public void setAirQualityData(AirQualityData airQualityData) {
        this.airQualityData = airQualityData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(airQualityData);
    }

    public int describeContents() {
        return  0;
    }

}
