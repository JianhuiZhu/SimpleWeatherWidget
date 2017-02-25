
package com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AddressResult;

@Generated("org.jsonschema2pojo")
public class GeoCodingResponse implements Parcelable
{

    @SerializedName("results")
    @Expose

    private List<AddressResult> results = new ArrayList<AddressResult>();
    @SerializedName("status")
    @Expose
    private String status;
    public final static Parcelable.Creator<GeoCodingResponse> CREATOR = new Creator<GeoCodingResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GeoCodingResponse createFromParcel(Parcel in) {
            GeoCodingResponse instance = new GeoCodingResponse();
            in.readList(instance.results, (AddressResult.class.getClassLoader()));
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public GeoCodingResponse[] newArray(int size) {
            return (new GeoCodingResponse[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The results
     */
    public List<AddressResult> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<AddressResult> results) {
        this.results = results;
    }

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


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
        dest.writeValue(status);
    }

    public int describeContents() {
        return  0;
    }

}
