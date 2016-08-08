package com.robert.zhihu.dagger.module;

import android.content.Context;

import com.robert.zhihu.App;
import com.robert.zhihu.dagger.scope.ContextLife;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 2016/8/1.
 */
@Module
public class AppModule {

    App mApp;

    public AppModule(App app) {
        this.mApp = app;
    }

    @ContextLife()
    @Provides
    @Singleton
    public Context provideAppContext() {
        return mApp;
    }
}
