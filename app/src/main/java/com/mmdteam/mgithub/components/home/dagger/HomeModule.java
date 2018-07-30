package com.mmdteam.mgithub.components.home.dagger;

import com.mmdteam.mgithub.components.home.TabHomeFragment;
import com.mmdteam.mgithub.components.home.core.HomeModel;
import com.mmdteam.mgithub.components.home.core.HomePresenter;
import com.mmdteam.mgithub.components.home.core.HomeView;
import com.mmdteam.mgithub.http.UserService;
import com.mmdteam.mgithub.util.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class HomeModule {

    private TabHomeFragment homeFragment;

    public HomeModule(TabHomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @HomeScope
    @Provides
    public HomeView provideHomeView() {
        return new HomeView(homeFragment);
    }

    @HomeScope
    @Provides
    public HomeModel provideHomeModel(UserService userService) {
        return new HomeModel(userService);
    }

    @HomeScope
    @Provides
    public HomePresenter provideHomePresenter(HomeModel homeModel, HomeView homeView, RxSchedulers rxSchedulers) {
        CompositeSubscription subscriptions = new CompositeSubscription();
        return new HomePresenter(homeModel, homeView, rxSchedulers, subscriptions);
    }
}
