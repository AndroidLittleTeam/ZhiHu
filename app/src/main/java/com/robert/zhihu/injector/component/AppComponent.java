package com.robert.zhihu.injector.component;

import android.content.Context;

import com.robert.zhihu.data.DataManager;
import com.robert.zhihu.injector.module.AppModule;
import com.robert.zhihu.injector.scope.ContextLife;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 2016/8/1.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    //提供Application
    @ContextLife()
    Context getContext();

    DataManager getDataManager();
}
