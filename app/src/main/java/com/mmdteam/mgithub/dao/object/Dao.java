package com.mmdteam.mgithub.dao.object;

import android.content.Context;

import com.mmdteam.mgithub.App;

import java.util.HashMap;
import java.util.Map;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;

public class Dao {

    private Box box;
    private Query query;

    private static final Map<Class<?>, Dao> sInstanceMap = new HashMap<>();

    public static Dao instance(Context context, Class<?> c) {

        synchronized (sInstanceMap) {
            Dao instance = sInstanceMap.get(c);
            if (instance == null) {
                instance = new Dao(context, c);
                sInstanceMap.put(c, instance);
            }
            return instance;
        }
    }

    private Dao(Context context, Class<?> t) {
        BoxStore boxStore = ((App)(context.getApplicationContext())).getBoxStore();
        box = boxStore.boxFor(t);
        query = box.query().build();
    }

    public Box getBox() {
        return box;
    }

    public Query getQuery() {
        return query;
    }
}
