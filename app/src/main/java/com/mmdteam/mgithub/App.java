package com.mmdteam.mgithub;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;

import com.mmdteam.mgithub.inject.component.AppComponent;
import com.mmdteam.mgithub.inject.component.DaggerAppComponent;
import com.mmdteam.mgithub.inject.module.AppModule;
import com.mmdteam.mgithub.service.NetBroadCastReceiver;
import com.mmdteam.mgithub.util.NetHelper;


public class App extends Application {


    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AppData.INSTANCE.getSystemDefaultLocal();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        initNetWork();
    }


    /**
     * 初始化网络
     */
    private void initNetWork() {
        NetBroadCastReceiver receiver = new NetBroadCastReceiver();
        IntentFilter filter;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        } else {
            filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }

        registerReceiver(receiver, filter);

        NetHelper.INSTANCE.init(this);
    }


    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
