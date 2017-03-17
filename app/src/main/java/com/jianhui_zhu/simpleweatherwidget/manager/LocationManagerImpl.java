package com.jianhui_zhu.simpleweatherwidget.manager;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.jianhui_zhu.simpleweatherwidget.BuildConfig;
import com.jianhui_zhu.simpleweatherwidget.R;
import com.jianhui_zhu.simpleweatherwidget.dagger.APIModule;
import com.jianhui_zhu.simpleweatherwidget.dagger.DaggerServiceManagerComponent;
import com.jianhui_zhu.simpleweatherwidget.data_provider.GeoCodingAPI;
import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AddressResult;
import com.jianhui_zhu.simpleweatherwidget.data_provider.webresponse.GeoCodingResponse;
import com.jianhui_zhu.simpleweatherwidget.utils.StringFormatUtil;

import java.util.List;

import javax.inject.Inject;

import ru.solodovnikov.rxlocationmanager.RxLocationManager;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import static com.jianhui_zhu.simpleweatherwidget.utils.StringFormatUtil.*;
/**
 * Created by jianhuizhu on 2017-02-08.
 */

public class LocationManagerImpl implements com.jianhui_zhu.simpleweatherwidget.manager.LocationManager{
    private static final String OK = "OK";
    @Inject
    GeoCodingAPI geoCodingAPI;
    private RxLocationManager rxLocationManager;
    private LocationManager locationManager;

    public LocationManagerImpl(Context context) {
        rxLocationManager = new RxLocationManager(context);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        DaggerServiceManagerComponent.builder().aPIModule(new APIModule()).build().inject(this);
    }

    private Location getAnyLastKnownLocation() {
        List<String> providers = locationManager.getAllProviders();
        for (String provider : providers) {
            @SuppressWarnings("MissingPermission")
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                return location;
            }
        }
        return null;
    }

    private Observable<Location> requestLocationUpdate() {
        return rxLocationManager.requestLocation(LocationManager.GPS_PROVIDER)
                .onErrorResumeNext(throwable -> {
                    throwable.printStackTrace();
                    return rxLocationManager.requestLocation(LocationManager.NETWORK_PROVIDER);
                });
    }
    @Override
    public Observable<Location> getLocation() {
        Location location = getAnyLastKnownLocation();
        if (location != null) {
            return Observable.just(location);
        }
        return requestLocationUpdate();
    }


    @Override
    public Observable<AddressResult> getAddressResult(final Context context) {
        Location location = getAnyLastKnownLocation();
        if(location != null){
            return queryAddressResult(location.getLatitude(),location.getLongitude(),context);
        }else{
            return requestLocationUpdate()
                    .flatMap(updatedLocation -> queryAddressResult(updatedLocation.getLatitude(),updatedLocation.getLongitude(),context) );
        }
    }

    @Override
    public Observable<AddressResult> getAddressResult(Context context, double lat, double lon) {
        return queryAddressResult(lat,lon,context);
    }


    private Observable<AddressResult> queryAddressResult(double lat, double lon,Context context){
        String latlng = weatherLocationStringBuilder(lat,lon);
        return geoCodingAPI.getLocationInformationByLatLon(latlng, context.getString(R.string.googlemapapikey))
                .flatMap(geoCodingResponse -> Observable.just(geoCodingResponse.getResults().get(0)))
                .doOnError(Throwable::printStackTrace);
    }
}
