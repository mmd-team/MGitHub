package com.mmdteam.mgithub.inject.component;

import com.mmdteam.mgithub.inject.FragmentScope;
import com.mmdteam.mgithub.inject.module.FragmentModule;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {
}
