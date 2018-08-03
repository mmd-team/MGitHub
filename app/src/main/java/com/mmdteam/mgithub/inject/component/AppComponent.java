package com.mmdteam.mgithub.inject.component;

import android.content.Context;

import com.mmdteam.mgithub.dao.DaoSession;
import com.mmdteam.mgithub.http.LoginService;
import com.mmdteam.mgithub.http.UserService;
import com.mmdteam.mgithub.inject.module.ApiServiceModule;
import com.mmdteam.mgithub.inject.module.AppModule;
import com.mmdteam.mgithub.inject.module.NetworkModule;
import com.mmdteam.mgithub.inject.module.RxModule;
import com.mmdteam.mgithub.util.IconUtils;
import com.mmdteam.mgithub.util.rx.RxSchedulers;

import dagger.Component;
import io.objectbox.BoxStore;

@AppScope
@Component(modules = {AppModule.class, NetworkModule.class, RxModule.class, ApiServiceModule.class})
public interface AppComponent {

    Context getContext();

    IconUtils getIcon();

    LoginService loginService();

    UserService userService();

    RxSchedulers rxSchedulers();

    DaoSession daoSession();

    BoxStore boxStore();

}
