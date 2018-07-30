package com.mmdteam.mgithub.inject.module;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mmdteam.mgithub.AppConfig;
import com.mmdteam.mgithub.inject.component.AppScope;
import com.mmdteam.mgithub.util.rx.AppRxSchedulers;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    public NetworkModule() {

    }

    @Provides
    @AppScope
    Cache provideHttpCache(File file) {
        return new Cache(file, AppConfig.HTTP_MAX_CACHE_SIZE);
    }

    @Provides
    @AppScope
    File provideFile(Context context) {
        return context.getFilesDir();
    }

    @Provides
    @AppScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @AppScope
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor logger, Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(logger);
        builder.connectTimeout(AppConfig.HTTP_TIME_OUT, TimeUnit.SECONDS);
        builder.cache(cache);
        return builder.build();
    }

    @AppScope
    @Provides
    HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }


    @AppScope
    @Provides
    RxJavaCallAdapterFactory provideAdapter() {
        return RxJavaCallAdapterFactory.createWithScheduler(AppRxSchedulers.INTERNET_SCHEDULERS);
    }

    @Provides
    @AppScope
    GsonConverterFactory provideGsonClient(Gson gson) {
        return GsonConverterFactory.create(gson);
    }
}
