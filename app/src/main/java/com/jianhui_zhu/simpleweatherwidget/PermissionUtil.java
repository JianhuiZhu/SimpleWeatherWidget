package com.jianhui_zhu.simpleweatherwidget;

import android.*;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by jianhuizhu on 2017-02-02.
 */

public final class PermissionUtil {
    public static final String ACTION_TAG = "ACTION";
    public static final String REQUEST_PERMISSION = "REQUEST_PERMISSION";
    public static final int REQUEST_CODE = 1;
    public static final String PRESET_AUTHORIZATION_PREFERENCE = "PERMISSION_PREFERENCE";
    public static final String DISALLOW = "DISALLOW";
    public static final String ALLOW = "ALLOW";



    public static boolean isLocationPermissionGranted(Context context){
        int coarseLocationPermissionResult = ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int fineLocationPermissionResult = ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION);
        return coarseLocationPermissionResult == PackageManager.PERMISSION_GRANTED && fineLocationPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    public static AlertDialog getPermissionRequestDialog(final Activity activity){
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                requestPermission(activity);

            }
        };
        return new AlertDialog.Builder(activity,R.style.AppTheme)
                .setTitle(R.string.permission_request_title)
                .setMessage(R.string.permission_request_content)
                .setPositiveButton(R.string.make_decision,listener)
                .create();
    }

    public static boolean isAcquiringPermission(Intent intent){

        String action = intent.getStringExtra(ACTION_TAG);
        return action != null && action.equals(REQUEST_PERMISSION);
    }
    public static void requestPermission(Activity activity){
        ActivityCompat.requestPermissions(activity,
                new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_CODE);
    }
}
