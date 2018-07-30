package com.mmdteam.mgithub.inject.module;

import com.mmdteam.mgithub.util.rx.AppRxSchedulers;
import com.mmdteam.mgithub.util.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {

    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}
