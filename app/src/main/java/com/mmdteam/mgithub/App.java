package com.mmdteam.mgithub;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;

import com.mmdteam.mgithub.dao.object.MyObjectBox;
import com.mmdteam.mgithub.inject.component.AppComponent;
import com.mmdteam.mgithub.inject.component.DaggerAppComponent;
import com.mmdteam.mgithub.inject.module.AppModule;
import com.mmdteam.mgithub.service.NetBroadCastReceiver;
import com.mmdteam.mgithub.util.NetHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;


public class App extends Application {


    private static AppComponent appComponent;


    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        AppData.INSTANCE.getSystemDefaultLocal();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(this);
        }
        initNetWork();
        initSensorsData();
    }

    private void initSensorsData() {
        SensorsDataAPI.sharedInstance(this, AppConfig.SENSORS_DATA_ADDRESS, AppConfig.DEBUG_MODE);
        SensorsDataAPI.sharedInstance().trackFragmentAppViewScreen();
        try {
            // 打开自动采集, 并指定追踪哪些 AutoTrack 事件
            List<SensorsDataAPI.AutoTrackEventType> eventTypeList = new ArrayList<>();
            // $AppStart
            eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_START);
            // $AppEnd
            eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_END);
            // $AppViewScreen
            eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN);
            // $AppClick
            eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_CLICK);
            SensorsDataAPI.sharedInstance().enableAutoTrack(eventTypeList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        trackInstallation();
    }

    private void trackInstallation() {
        SensorsDataAPI.sharedInstance().trackInstallation("AppInstall");
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

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
