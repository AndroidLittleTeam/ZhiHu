package com.robert.zhihu.injector.component;

import android.app.Activity;
import android.content.Context;

import com.robert.zhihu.injector.module.ActivityModule;
import com.robert.zhihu.injector.scope.ContextLife;
import com.robert.zhihu.injector.scope.PerActivity;
import com.robert.zhihu.ui.activity.MainActivity;
import com.robert.zhihu.ui.activity.WebActivity;

import dagger.Component;

/**
 * Created by robert on 2016/8/8.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(WebActivity webActivity);

    Activity getActivity();

    @ContextLife("Activity")
    Context getContext();
}
