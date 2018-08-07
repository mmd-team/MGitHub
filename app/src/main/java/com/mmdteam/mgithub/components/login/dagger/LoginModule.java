package com.mmdteam.mgithub.components.login.dagger;

import com.mmdteam.mgithub.components.login.LoginActivity;
import com.mmdteam.mgithub.components.login.core.LoginModel;
import com.mmdteam.mgithub.components.login.core.LoginPresenter;
import com.mmdteam.mgithub.components.login.core.LoginView;
import com.mmdteam.mgithub.dao.DaoSession;
import com.mmdteam.mgithub.http.LoginService;
import com.mmdteam.mgithub.http.UserService;
import com.mmdteam.mgithub.util.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class LoginModule {

    private LoginActivity loginContext;

    public LoginModule(LoginActivity loginContext) {
        this.loginContext = loginContext;
    }

    @LoginScope
    @Provides
    LoginView provideLoginView() {
        return new LoginView(loginContext);
    }

    @LoginScope
    @Provides
    LoginPresenter providerLoginPresenter(RxSchedulers schedulers, LoginView loginView, LoginModel loginModel) {
        CompositeSubscription subscriptions = new CompositeSubscription();
        return new LoginPresenter(schedulers, loginView, loginModel, subscriptions);
    }

    @LoginScope
    @Provides
    LoginModel providerLoginModel(LoginService loginService, UserService userService, DaoSession daoSession) {
        return new LoginModel(loginContext, loginService, userService, daoSession);
    }

    @LoginScope
    @Provides
    LoginActivity providerContext() {
        return loginContext;
    }
}
