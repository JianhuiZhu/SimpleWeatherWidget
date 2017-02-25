
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import java.util.ArrayList;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Alert implements Parcelable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("regions")
    @Expose
    private ArrayList<String> regions = new ArrayList<String>();
    @SerializedName("severity")
    @Expose
    private String severity;
    @SerializedName("time")
    @Expose
    private int time;
    @SerializedName("expires")
    @Expose
    private int expires;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("uri")
    @Expose
    private String uri;
    public final static Parcelable.Creator<Alert> CREATOR = new Creator<Alert>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Alert createFromParcel(Parcel in) {
            Alert instance = new Alert();
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.regions, (java.lang.String.class.getClassLoader()));
            instance.severity = ((String) in.readValue((String.class.getClassLoader())));
            instance.time = ((int) in.readValue((int.class.getClassLoader())));
            instance.expires = ((int) in.readValue((int.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.uri = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Alert[] newArray(int size) {
            return (new Alert[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The regions
     */
    public ArrayList<String> getRegions() {
        return regions;
    }

    /**
     * 
     * @param regions
     *     The regions
     */
    public void setRegions(ArrayList<String> regions) {
        this.regions = regions;
    }

    /**
     * 
     * @return
     *     The severity
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * 
     * @param severity
     *     The severity
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * 
     * @return
     *     The time
     */
    public int getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The expires
     */
    public int getExpires() {
        return expires;
    }

    /**
     * 
     * @param expires
     *     The expires
     */
    public void setExpires(int expires) {
        this.expires = expires;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *     The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeList(regions);
        dest.writeValue(severity);
        dest.writeValue(time);
        dest.writeValue(expires);
        dest.writeValue(description);
        dest.writeValue(uri);
    }

    public int describeContents() {
        return  0;
    }

}
