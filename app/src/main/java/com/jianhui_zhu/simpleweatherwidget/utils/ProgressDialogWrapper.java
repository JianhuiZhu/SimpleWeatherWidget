package com.jianhui_zhu.simpleweatherwidget.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

import com.jianhui_zhu.simpleweatherwidget.R;

/**
 * Created by jianhuizhu on 2017-03-07.
 */

public class ProgressDialogWrapper {
    private ProgressDialog progressDialog;
    public ProgressDialogWrapper(Context context){
            progressDialog = new ProgressDialog(context);
            progressDialog.setIcon(R.drawable.ic_cloud_download);
            progressDialog.setMessage(context.getString(R.string.refreshing_data));
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
    }
    public void dismiss(){
        progressDialog.dismiss();
    }
}
