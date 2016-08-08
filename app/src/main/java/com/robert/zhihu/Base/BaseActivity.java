package com.robert.zhihu.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.robert.zhihu.App;
import com.robert.zhihu.dagger.component.ActivityComponent;
import com.robert.zhihu.dagger.component.AppComponent;
import com.robert.zhihu.dagger.component.DaggerActivityComponent;
import com.robert.zhihu.dagger.module.ActivityModule;

/**
 * Created by robert on 2016/8/8.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityComponent mActivityComponent;
    protected Context mContext;

    protected IPresenter mIPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        AppComponent appComponent = App.getAppComponent();
        mActivityComponent = DaggerActivityComponent
                .builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build();
        appComponent.inject(this);
        initInjector();
        initViewWithListener();

    }

    protected abstract void initInjector();

    protected abstract void initViewWithListener();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIPresenter != null) {
            mIPresenter.detachView();
        } else {
            YLog.wtf("yuyidong", "this activity " + this.getClass().getSimpleName() + "  没有设置 IPresenter!!!");
        }
    }
}
