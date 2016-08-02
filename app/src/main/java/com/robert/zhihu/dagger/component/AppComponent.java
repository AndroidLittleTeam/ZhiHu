package com.robert.zhihu.dagger.component;

import com.robert.zhihu.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 2016/8/1.
 */
@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

}
