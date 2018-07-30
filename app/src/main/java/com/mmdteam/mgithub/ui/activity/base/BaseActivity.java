package com.mmdteam.mgithub.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.mmdteam.mgithub.App;
import com.mmdteam.mgithub.inject.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.weiyii.uilib.arch.UIActivity;

public abstract class BaseActivity extends UIActivity {

    public AppComponent getAppComponent() {
        return App.getAppComponent();
    }

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContent() != 0) {
            setContentView(getContent());
        }
        unbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    @LayoutRes
    abstract protected int getContent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * 初始化view
     */
    @CallSuper
    protected void initView(Bundle savedInstanceState) {


    }

}
