package com.mmdteam.mgithub.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.weiyii.uilib.arch.UIFragment;

public abstract class BaseFragment extends UIFragment {


    Unbinder unbinder;

    private boolean isPagerFragment = false;
    private boolean isFragmentShowed = false;


    public BaseFragment() {
    }


    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initFragment(Bundle savedInstanceState);

    protected void initFragment(View view, Bundle savedInstanceState) {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View fragmentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (fragmentView == null) {
            fragmentView = inflater.inflate(getLayoutId(), container, false);
            unbinder = ButterKnife.bind(this, fragmentView);
            initFragment(fragmentView, savedInstanceState);
            initFragment(savedInstanceState);
        }
        return fragmentView;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != fragmentView) {
            ((ViewGroup) fragmentView.getParent()).removeView(fragmentView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    public void onFragmentShowed() {
        isFragmentShowed = true;
    }

    public void onFragmentHided() {
        isFragmentShowed = false;
    }


    public boolean isPagerFragment() {
        return isPagerFragment;
    }

    public boolean isFragmentShowed() {
        return isFragmentShowed;
    }


}
