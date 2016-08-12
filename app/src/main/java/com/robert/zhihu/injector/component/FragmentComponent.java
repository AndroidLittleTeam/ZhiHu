package com.robert.zhihu.injector.component;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.robert.zhihu.injector.module.FragmentModule;
import com.robert.zhihu.injector.scope.ContextLife;
import com.robert.zhihu.injector.scope.PerFragment;
import com.robert.zhihu.ui.fragment.ItemFragment;

import dagger.Component;

/**
 * Created by robert on 2016/8/8.
 */
@PerFragment
@Component(dependencies = AppComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(ItemFragment itemFragment);

    @ContextLife("Activity")
    Context getContext();

    Fragment getFragment();

    Activity getActivity();

}
