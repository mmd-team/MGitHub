package com.mmdteam.mgithub;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

public interface AppConfig {


    interface Config {
        String GIT_BASE_URL = "https://github.com/";
        String GIT_API_BASE_URL = "https://api.github.com/";
        String DB_NAME = "MGitHub.db";
    }

    int HTTP_TIME_OUT = 32 * 1000;
    int HTTP_MAX_CACHE_SIZE = 32 * 1024 * 1024;
    int IMAGE_MAX_CACHE_SIZE = 32 * 1024 * 1024;
    int CACHE_MAX_AGE = 4 * 7 * 24 * 60 * 60;
    String M_GITHUB_CLIENT_ID = BuildConfig.M_GITHUB_CLIENT_ID;
    String M_GITHUB_CLIENT_SECRET = BuildConfig.M_GITHUB_CLIENT_SECRET;
    String BUGLY_APP_ID = BuildConfig.BUGLY_ID;


    String REDIRECT_URL = "http://other.mmdteam.com/github/callback";

    String SENSORS_DATA_ADDRESS = BuildConfig.SENSORS_DATA_ADDRESS;
    SensorsDataAPI.DebugMode DEBUG_MODE = BuildConfig.DEBUG ? SensorsDataAPI.DebugMode.DEBUG_AND_TRACK : SensorsDataAPI.DebugMode.DEBUG_OFF;
}
