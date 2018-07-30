package com.mmdteam.mgithub.components.home.core;

import com.mmdteam.mgithub.model.Event;
import com.mmdteam.mgithub.util.rx.RxSchedulers;

import java.util.ArrayList;

import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter {

    private HomeModel model;
    private HomeView view;
    private RxSchedulers rxSchedulers;
    private CompositeSubscription subscriptions;


    public HomePresenter(HomeModel model, HomeView view, RxSchedulers rxSchedulers, CompositeSubscription subscriptions) {
        this.model = model;
        this.view = view;
        this.rxSchedulers = rxSchedulers;
        this.subscriptions = subscriptions;
    }


    public void onCreate() {

    }


    public void getUserEvents(String token, int id) {
        Subscription subscribe = model.getUserEvents(token, id)
                .observeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(new Subscriber<Response<ArrayList<Event>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<ArrayList<Event>> arrayListResponse) {
                        view.getEvents(arrayListResponse.body());
                    }
                });
        subscriptions.add(subscribe);
    }

    public void onDestroy() {
        subscriptions.clear();
    }
}
