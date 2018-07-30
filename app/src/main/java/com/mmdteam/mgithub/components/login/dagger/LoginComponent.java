package com.mmdteam.mgithub.components.login.dagger;

import com.mmdteam.mgithub.components.login.LoginActivity;
import com.mmdteam.mgithub.inject.component.AppComponent;

import dagger.Component;

@LoginScope
@Component(dependencies = {AppComponent.class}, modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginActivity context);
}
