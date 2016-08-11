package com.robert.zhihu.injector.component;

import android.content.Context;

import com.robert.zhihu.Base.BaseActivity;
import com.robert.zhihu.Base.BaseFragment;
import com.robert.zhihu.data.api.HotApi;
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
    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

    //提供Application
    @ContextLife()
    Context getContext();

    HotApi getHotApi();
}
