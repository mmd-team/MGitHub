package com.mmdteam.mgithub.inject.module;

import android.content.Context;

import com.mmdteam.mgithub.inject.FragmentScope;
import com.mmdteam.mgithub.ui.fragment.base.BaseFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private BaseFragment fragment;

    public FragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public BaseFragment provideFragment() {
        return fragment;
    }

    @Provides
    @FragmentScope
    public Context provideContext() {
        return fragment.getContext();
    }
}
