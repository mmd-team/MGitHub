package com.mmdteam.mgithub.inject.module;

import com.mmdteam.mgithub.http.LoginService;
import com.mmdteam.mgithub.http.UserService;
import com.mmdteam.mgithub.inject.component.AppScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiServiceModule {


    private static String mBaseUrl = "https://api.github.com/";


    @Provides
    @AppScope
    UserService providerUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }


    @Provides
    @AppScope
    LoginService providerLoginService(Retrofit retrofit) {
        return retrofit.create(LoginService.class);
    }

    @Provides
    @AppScope
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, OkHttpClient okHttpClient, RxJavaCallAdapterFactory rxAdapter) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxAdapter)
                .client(okHttpClient)
                .build();
    }
}
