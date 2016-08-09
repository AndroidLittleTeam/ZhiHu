package com.robert.zhihu.injector.component;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.robert.zhihu.injector.module.ActivityModule;
import com.robert.zhihu.injector.scope.ContextLife;
import com.robert.zhihu.injector.scope.PerActivity;

import dagger.Component;

/**
 * Created by robert on 2016/8/8.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface FragmentComponent {


    @ContextLife("Activity")
    Context getContext();

    Fragment getFragment();

    Activity getActivity();

}
