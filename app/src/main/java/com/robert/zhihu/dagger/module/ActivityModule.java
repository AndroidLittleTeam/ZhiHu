package com.robert.zhihu.dagger.module;

import android.app.Activity;
import android.content.Context;

import com.robert.zhihu.dagger.scope.ContextLife;
import com.robert.zhihu.dagger.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 2016/8/8.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @PerActivity
    @Provides
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideContext() {
        return mActivity;
    }

}
