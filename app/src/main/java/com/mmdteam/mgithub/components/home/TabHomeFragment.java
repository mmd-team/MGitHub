package com.mmdteam.mgithub.components.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.mmdteam.mgithub.App;
import com.mmdteam.mgithub.R;
import com.mmdteam.mgithub.components.home.core.HomePresenter;
import com.mmdteam.mgithub.components.home.core.HomeView;
import com.mmdteam.mgithub.components.home.dagger.DaggerHomeComponent;
import com.mmdteam.mgithub.components.home.dagger.HomeModule;
import com.mmdteam.mgithub.ui.fragment.base.BaseFragment;

import javax.inject.Inject;

public class TabHomeFragment extends BaseFragment {


    @Inject
    HomeView view;
    @Inject
    HomePresenter presenter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_home;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHomeComponent.builder().appComponent(App.getAppComponent()).homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view.onCreate(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view.onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    @Override
    public void onFragmentShowed() {
        super.onFragmentShowed();
    }

    public void getUserEvents(String token, int id) {
        presenter.getUserEvents(token, id);
    }


}
