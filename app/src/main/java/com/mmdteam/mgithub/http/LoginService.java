package com.mmdteam.mgithub.http;


import android.support.annotation.NonNull;

import com.mmdteam.mgithub.model.AuthRequestModel;
import com.mmdteam.mgithub.model.BasicToken;
import com.mmdteam.mgithub.model.OauthToken;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface LoginService {

    @POST("authorizations")
    @Headers("Accept: application/json")
    Observable<Response<BasicToken>> authorizations(
            @Header("Authorization") String header,
            @NonNull @Body AuthRequestModel authRequestModel);

    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    Observable<Response<OauthToken>> getAccessToken(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("code") String code,
            @Query("state") String state
    );


}
