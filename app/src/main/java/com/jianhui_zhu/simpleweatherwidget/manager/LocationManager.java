package com.jianhui_zhu.simpleweatherwidget.manager;

import android.content.Context;
import android.location.Location;

import com.jianhui_zhu.simpleweatherwidget.data_provider.model.AddressResult;

import rx.Observable;

/**
 * Created by jianhuizhu on 2017-02-25.
 */

public interface LocationManager {

    Observable<Location> getLocation();

    Observable<AddressResult> getAddressResult(Context context);

    Observable<AddressResult> getAddressResult(Context context,double lat, double lon);
}
