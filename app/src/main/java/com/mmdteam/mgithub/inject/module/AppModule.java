package com.mmdteam.mgithub.inject.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mmdteam.mgithub.AppConfig;
import com.mmdteam.mgithub.dao.DaoMaster;
import com.mmdteam.mgithub.dao.DaoSession;
import com.mmdteam.mgithub.inject.component.AppScope;
import com.mmdteam.mgithub.util.IconUtils;

import org.greenrobot.greendao.database.Database;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @AppScope
    public Context provideContext() {
        return context;
    }

    @NonNull
    @Provides
    @AppScope
    public IconUtils provideIcon() {
        return new IconUtils(context);
    }

    @NonNull
    @Provides
    @AppScope
    public DaoSession provideDaoSession() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, AppConfig.Config.DB_NAME, null);
        Database db = helper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }


}
