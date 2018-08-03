package com.mmdteam.mgithub.dao.object;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;

public class LoginUserDao {
    private Box<LoginUser> box;
    private Query<LoginUser> query;

    public LoginUserDao(BoxStore boxStore) {
        box = boxStore.boxFor(LoginUser.class);
        query = box.query().order(LoginUser_.username).build();
    }

    public Box<LoginUser> getBox() {
        return box;
    }

    public Query<LoginUser> getQuery() {
        return query;
    }
}
