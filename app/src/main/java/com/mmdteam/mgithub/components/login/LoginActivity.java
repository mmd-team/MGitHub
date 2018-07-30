package com.mmdteam.mgithub.components.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mmdteam.mgithub.App;
import com.mmdteam.mgithub.R;
import com.mmdteam.mgithub.components.login.core.LoginPresenter;
import com.mmdteam.mgithub.components.login.core.LoginView;
import com.mmdteam.mgithub.components.login.dagger.DaggerLoginComponent;
import com.mmdteam.mgithub.components.login.dagger.LoginModule;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginView loginView;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        DaggerLoginComponent.builder().appComponent(App.getAppComponent()).loginModule(new LoginModule(this)).build().inject(this);
        setContentView(loginView.view());
        presenter.onCreate();
    }

    public void login() {
        presenter.login();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginView.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.slide_out_right);
    }

}
