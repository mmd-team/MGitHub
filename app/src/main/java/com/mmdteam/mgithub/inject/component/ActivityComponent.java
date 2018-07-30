package com.mmdteam.mgithub.inject.component;

import com.mmdteam.mgithub.components.login.LoginActivity;
import com.mmdteam.mgithub.inject.ActivityScope;
import com.mmdteam.mgithub.inject.module.ActivityModule;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

//    void inject(LoginActivity loginActivity);

}
