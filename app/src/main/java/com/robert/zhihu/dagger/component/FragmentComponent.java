package com.robert.zhihu.dagger.component;

import com.robert.zhihu.dagger.module.ActivityModule;
import com.robert.zhihu.dagger.scope.PerActivity;

import dagger.Component;

/**
 * Created by robert on 2016/8/8.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
interface FragmentComponent {
}
