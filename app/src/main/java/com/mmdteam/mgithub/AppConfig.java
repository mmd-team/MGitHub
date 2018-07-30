package com.mmdteam.mgithub;

public class AppConfig {


    public interface Config {
        String GIT_BASE_URL = "https://github.com/";
        String GIT_API_BASE_URL = "https://api.github.com/";
        String DB_NAME = "MGitHub.db";
    }

    public static final int HTTP_TIME_OUT = 32 * 1000;
    public static final int HTTP_MAX_CACHE_SIZE = 32 * 1024 * 1024;
    public static final int IMAGE_MAX_CACHE_SIZE = 32 * 1024 * 1024;
    public static final int CACHE_MAX_AGE = 4 * 7 * 24 * 60 * 60;
    public static final String OPENHUB_CLIENT_ID = BuildConfig.OPENHUB_CLIENT_ID;
    public static final String OPENHUB_CLIENT_SECRET = BuildConfig.OPENHUB_CLIENT_SECRET;
    public final static String BUGLY_APPID = BuildConfig.BUGLY_ID;

    public static final String REDIRECT_URL = "https://github.com/ThirtyDegreesRay/OpenHub/CallBack";
}
