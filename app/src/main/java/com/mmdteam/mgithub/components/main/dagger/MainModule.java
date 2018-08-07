package com.mmdteam.mgithub.components.main.dagger;

import com.mmdteam.mgithub.components.main.MainActivity;
import com.mmdteam.mgithub.components.main.core.MainModel;
import com.mmdteam.mgithub.components.main.core.MainPresenter;
import com.mmdteam.mgithub.components.main.core.MainView;

import dagger.Module;
import dagger.Provides;
import io.objectbox.BoxStore;

@Module
public class MainModule {

    private MainActivity activity;

    public MainModule(MainActivity activity) {
        this.activity = activity;
    }

    @MainScope
    @Provides
    MainActivity providerContext() {
        return activity;
    }

    @MainScope
    @Provides
    MainPresenter providerPresenter() {
        return new MainPresenter(activity);
    }

    @MainScope
    @Provides
    MainModel providerMainModel() {
        return new MainModel();
    }

    @MainScope
    @Provides
    MainView providerMainView() {
        return new MainView();
    }
}
