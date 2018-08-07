package com.mmdteam.mgithub.components.main.dagger;

import com.mmdteam.mgithub.components.main.MainActivity;
import com.mmdteam.mgithub.inject.component.AppComponent;

import dagger.Component;

@MainScope
@Component(dependencies = {AppComponent.class}, modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity activity);
}
