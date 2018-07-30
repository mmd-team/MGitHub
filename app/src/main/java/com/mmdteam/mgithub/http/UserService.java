package com.mmdteam.mgithub.http;

import android.support.annotation.NonNull;

import com.mmdteam.mgithub.model.Event;
import com.mmdteam.mgithub.model.User;

import java.util.ArrayList;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface UserService {

    @NonNull
    @GET("user")
    Observable<Response<User>> getPersonInfo(
            @Header("Authorization") String token,
            @Header("forceNetWork") boolean forceNetWork
    );

    @NonNull
    @GET("user/{user_id}/received_events")
    Observable<Response<ArrayList<Event>>> getNewsEvent(
            @Header("forceNetWork") boolean forceNetWork,
            @Header("Authorization") String token,
            @Path("user_id") int id,
            @Query("page") int page
    );
}
