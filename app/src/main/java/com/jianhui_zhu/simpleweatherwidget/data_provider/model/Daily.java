
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Daily implements Parcelable
{
    @Override
    public String toString() {
        return "Daily{" +
                "summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                ", data=" + data.toString() +
                '}';
    }


    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("data")
    @Expose
    private ArrayList<DailyDataPoint> data = new ArrayList<DailyDataPoint>();
    public final static Parcelable.Creator<Daily> CREATOR = new Creator<Daily>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Daily createFromParcel(Parcel in) {
            Daily instance = new Daily();
            instance.summary = ((String) in.readValue((String.class.getClassLoader())));
            instance.icon = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.data, (DailyDataPoint.class.getClassLoader()));
            return instance;
        }

        public Daily[] newArray(int size) {
            return (new Daily[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary
     *     The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The data
     */
    public List<DailyDataPoint> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(ArrayList<DailyDataPoint> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(summary);
        dest.writeValue(icon);
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
