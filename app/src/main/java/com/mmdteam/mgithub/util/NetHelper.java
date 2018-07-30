package com.mmdteam.mgithub.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public enum NetHelper {

    @SuppressLint("StaticFieldLeak") INSTANCE;

    public static final int TYPE_DISCONNECT = 0;
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;

    private Context mContext;
    private int mCurNetStatus;

    public void init(Context context) {
        this.mContext = context;
        checkNet();
    }

    public void checkNet() {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                        this.mCurNetStatus = TYPE_WIFI;
                    }
                    if (info.getType() == TYPE_MOBILE) {
                        this.mCurNetStatus = TYPE_MOBILE;
                    }
                }
            } else {
                this.mCurNetStatus = TYPE_DISCONNECT;
            }
        }
    }

    @NonNull
    public Boolean getNetEnabled() {
        return mCurNetStatus == TYPE_MOBILE || mCurNetStatus == TYPE_WIFI;
    }

    public int getNetStatus() {
        return mCurNetStatus;
    }

}
