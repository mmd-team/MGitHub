package com.mmdteam.mgithub.components.home.dagger;

import com.mmdteam.mgithub.components.home.TabHomeFragment;
import com.mmdteam.mgithub.inject.component.AppComponent;

import dagger.Component;

@HomeScope
@Component(dependencies = {AppComponent.class}, modules = {HomeModule.class})
public interface HomeComponent {
    void inject(TabHomeFragment homeFragment);
}
