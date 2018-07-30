package com.mmdteam.mgithub.components.login.core;

import com.mmdteam.mgithub.AppData;
import com.mmdteam.mgithub.components.login.LoginActivity;
import com.mmdteam.mgithub.dao.AuthUser;
import com.mmdteam.mgithub.dao.AuthUserDao;
import com.mmdteam.mgithub.dao.DaoSession;
import com.mmdteam.mgithub.http.LoginService;
import com.mmdteam.mgithub.http.UserService;
import com.mmdteam.mgithub.model.AuthRequestModel;
import com.mmdteam.mgithub.model.BasicToken;
import com.mmdteam.mgithub.model.User;
import com.mmdteam.mgithub.util.StringUtils;

import java.util.Date;

import retrofit2.Response;
import rx.Observable;

public class LoginModel {

    private LoginActivity loginContext;
    private LoginService loginService;
    private UserService userService;
    private DaoSession daoSession;

    public LoginModel(LoginActivity loginContext, LoginService loginService, UserService userService, DaoSession daoSession) {
        this.loginContext = loginContext;
        this.loginService = loginService;
        this.userService = userService;
        this.daoSession = daoSession;
    }

    public Observable<Response<BasicToken>> login(String token) {
        String auth = token.startsWith("Basic") ? token : "token " + token;
        AuthRequestModel authRequestModel = AuthRequestModel.generate();
        return loginService.authorizations(auth, authRequestModel);
    }

    public Observable<Response<User>> userInfo(String token) {
        String auth = token.startsWith("Basic") ? token : "token " + token;
        return userService.getPersonInfo(auth, true);
    }

    public void saveUserInfo(BasicToken basicToken, User userInfo) {
        String updateSql = "UPDATE " + daoSession.getAuthUserDao().getTablename()
                + " SET " + AuthUserDao.Properties.Selected.columnName + " = 0";
        daoSession.getAuthUserDao().getDatabase().execSQL(updateSql);
        String deleteExistsSql = "DELETE FROM " + daoSession.getAuthUserDao().getTablename()
                + " WHERE " + AuthUserDao.Properties.LoginId.columnName
                + " = '" + userInfo.getLogin() + "'";
        daoSession.getAuthUserDao().getDatabase().execSQL(deleteExistsSql);
        AuthUser authUser = new AuthUser();
        String scope = StringUtils.listToString(basicToken.getScopes(), ",");
        Date date = new Date();
        authUser.setAccessToken(basicToken.getToken());
        authUser.setScope(scope);
        authUser.setAuthTime(date);
        authUser.setExpireIn(360 * 24 * 60 * 60);
        authUser.setSelected(true);
        authUser.setId(userInfo.getId());
        authUser.setLoginId(userInfo.getLogin());
        authUser.setName(userInfo.getName());
        authUser.setAvatar(userInfo.getAvatarUrl());
        daoSession.getAuthUserDao().insert(authUser);
//        Toasty.normal(loginContext, basicToken.getToken()).show();
        AppData.INSTANCE.setAuthUser(authUser);
        AppData.INSTANCE.setLoggedUser(userInfo);
    }
}
