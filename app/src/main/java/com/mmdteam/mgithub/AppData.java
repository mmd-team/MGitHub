package com.mmdteam.mgithub;


import android.support.annotation.Nullable;

import com.mmdteam.mgithub.dao.AuthUser;
import com.mmdteam.mgithub.model.User;

import java.util.Locale;

public enum AppData {

    INSTANCE;

    Locale systemDefaultLocal;
    //    @AutoAccess(dataName = "appData_loggedUser")
    User loggedUser;
    //    @AutoAccess(dataName = "appData_authUser")
    AuthUser authUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    @Nullable
    public String getAccessToken() {
        return authUser == null ? null : authUser.getAccessToken();
    }


    public Locale getSystemDefaultLocal() {
        if (systemDefaultLocal == null) {
            systemDefaultLocal = Locale.getDefault();
        }
        return systemDefaultLocal;
    }
}
