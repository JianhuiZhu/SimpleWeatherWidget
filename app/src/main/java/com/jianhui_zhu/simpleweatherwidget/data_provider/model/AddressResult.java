
package com.jianhui_zhu.simpleweatherwidget.data_provider.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class AddressResult implements Parcelable
{

    @SerializedName("address_components")
    @Expose

    private List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;
    @SerializedName("geometry")
    @Expose

    private Geometry geometry;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("types")
    @Expose

    private List<String> types = new ArrayList<String>();
    public final static Parcelable.Creator<AddressResult> CREATOR = new Creator<AddressResult>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AddressResult createFromParcel(Parcel in) {
            AddressResult instance = new AddressResult();
            in.readList(instance.addressComponents, (com.jianhui_zhu.simpleweatherwidget.data_provider.model.AddressComponent.class.getClassLoader()));
            instance.formattedAddress = ((String) in.readValue((String.class.getClassLoader())));
            instance.geometry = ((Geometry) in.readValue((Geometry.class.getClassLoader())));
            instance.placeId = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.types, (java.lang.String.class.getClassLoader()));
            return instance;
        }

        public AddressResult[] newArray(int size) {
            return (new AddressResult[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The addressComponents
     */
    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    /**
     * 
     * @param addressComponents
     *     The address_components
     */
    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    /**
     * 
     * @return
     *     The formattedAddress
     */
    public String getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * 
     * @param formattedAddress
     *     The formatted_address
     */
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    /**
     * 
     * @return
     *     The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * 
     * @param geometry
     *     The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * 
     * @return
     *     The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * 
     * @param placeId
     *     The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     * 
     * @return
     *     The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(addressComponents);
        dest.writeValue(formattedAddress);
        dest.writeValue(geometry);
        dest.writeValue(placeId);
        dest.writeList(types);
    }

    public int describeContents() {
        return  0;
    }

}
