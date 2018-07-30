package com.mmdteam.mgithub.components.home.core;

import com.mmdteam.mgithub.http.UserService;
import com.mmdteam.mgithub.model.Event;

import java.util.ArrayList;

import retrofit2.Response;
import rx.Observable;

public class HomeModel {

    private UserService userService;

    public HomeModel(UserService userService) {
        this.userService = userService;
    }

    public Observable<Response<ArrayList<Event>>> getUserEvents(String token, int id) {
        String auth = token.startsWith("Basic") ? token : "token " + token;
        return userService.getNewsEvent(true, auth, id, 1);
    }

}
