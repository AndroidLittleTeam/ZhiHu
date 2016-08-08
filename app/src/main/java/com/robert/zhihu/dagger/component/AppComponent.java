package com.robert.zhihu.dagger.component;

import android.content.Context;

import com.robert.zhihu.Base.BaseActivity;
import com.robert.zhihu.dagger.module.AppModule;
import com.robert.zhihu.dagger.scope.ContextLife;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 2016/8/1.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(BaseActivity baseActivity);

    //提供Application
    @ContextLife()
    Context getContext();
}
