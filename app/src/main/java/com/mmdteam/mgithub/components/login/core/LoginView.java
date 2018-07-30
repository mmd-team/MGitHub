package com.mmdteam.mgithub.components.login.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mmdteam.mgithub.R;
import com.mmdteam.mgithub.components.login.LoginActivity;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

public class LoginView {

    private View view;
    private Unbinder unbinder;

    private LoginActivity activity;
    private QMUITipDialog tipDialog;

    @Inject
    public LoginView(LoginActivity activity) {
        this.activity = activity;
        FrameLayout layout = new FrameLayout(activity);
        layout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.activity_login, layout, true);
        unbinder = ButterKnife.bind(this, view);
    }

    public View view() {
        return view;
    }

    void getLogin(String s) {
        tipDialog.dismiss();
        Toasty.normal(activity, s).show();
        activity.finish();
    }

    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    @OnClick(R.id.login_btn)
    void login(View view) {
        tipDialog = new QMUITipDialog.Builder(activity)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在登录")
                .create();
        tipDialog.show();
        activity.login();
    }

}
