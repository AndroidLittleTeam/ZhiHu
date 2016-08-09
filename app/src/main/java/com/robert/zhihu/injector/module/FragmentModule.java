package com.robert.zhihu.injector.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.robert.zhihu.injector.scope.ContextLife;
import com.robert.zhihu.injector.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 2016/8/9.
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideContext() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Fragment provideFramgent() {
        return mFragment;
    }

    @Provides
    @PerFragment
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

}
