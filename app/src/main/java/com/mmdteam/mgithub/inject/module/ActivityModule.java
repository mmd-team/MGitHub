package com.mmdteam.mgithub.inject.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.mmdteam.mgithub.inject.ActivityScope;
import com.mmdteam.mgithub.ui.activity.base.BaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public BaseActivity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    public Context provideContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    public FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }
}
