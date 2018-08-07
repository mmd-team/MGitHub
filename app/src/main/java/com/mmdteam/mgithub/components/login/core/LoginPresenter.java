package com.mmdteam.mgithub.components.login.core;

import com.mmdteam.mgithub.dao.object.Dao;
import com.mmdteam.mgithub.dao.object.LoginUser;
import com.mmdteam.mgithub.model.BasicToken;
import com.mmdteam.mgithub.model.User;
import com.mmdteam.mgithub.util.rx.RxSchedulers;

import io.objectbox.Box;
import okhttp3.Credentials;
import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class LoginPresenter {

    private LoginView view;
    private LoginModel model;
    private RxSchedulers rxSchedulers;
    private CompositeSubscription subscriptions;


    public LoginPresenter(RxSchedulers schedulers, LoginView view, LoginModel model, CompositeSubscription sub) {
        this.rxSchedulers = schedulers;
        this.view = view;
        this.model = model;
        this.subscriptions = sub;
    }

    public void onCreate() {
    }

    public void login() {
        subscriptions.add(login1());
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    private Subscription login1() {
        String token = Credentials.basic("weiyinouon@163.com", "a5437650!");
        return model.login(token).observeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(new Subscriber<Response<BasicToken>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getLogin(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<BasicToken> basicTokenResponse) {
                        BasicToken basicToken = basicTokenResponse.body();
                        Box box = Dao.instance(view.activity, LoginUser.class).getBox();
                        assert basicToken != null;

                        getUserInfo(basicToken);
                    }
                });
    }

    private void getUserInfo(BasicToken basicToken) {
        subscriptions.add(userInfo(basicToken));
    }

    private Subscription userInfo(final BasicToken basicToken) {
        return model.userInfo(basicToken.getToken())
                .observeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(new Subscriber<Response<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getLogin(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<User> userResponse) {
                        LoginUser loginUser = new LoginUser();
                        loginUser.setUsername("weiyinouon@163.com");
                        loginUser.setPassword("a5437650!");
                        model.saveUserInfo(basicToken, userResponse.body());
                        view.getLogin(userResponse.body().toString());
                    }
                });
    }


}
