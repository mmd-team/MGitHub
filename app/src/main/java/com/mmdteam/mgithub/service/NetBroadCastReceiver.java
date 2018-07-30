package com.mmdteam.mgithub.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.mmdteam.mgithub.common.AppEventBus;
import com.mmdteam.mgithub.common.Event;
import com.mmdteam.mgithub.util.NetHelper;

public class NetBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            int preNetStatus = NetHelper.INSTANCE.getNetStatus();
            NetHelper.INSTANCE.checkNet();
            int curNetStatus = NetHelper.INSTANCE.getNetStatus();
            AppEventBus.INSTANCE.getEventBus().post(new Event.NetChangedEvent(preNetStatus, curNetStatus));
        }
    }
}
