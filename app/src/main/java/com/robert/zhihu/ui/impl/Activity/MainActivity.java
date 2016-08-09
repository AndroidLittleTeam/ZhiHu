package com.robert.zhihu.ui.impl.Activity;

import com.robert.zhihu.Base.BaseActivity;
import com.robert.zhihu.R;
import com.robert.zhihu.presenter.MainPresenter;
import com.robert.zhihu.ui.MainView;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
        mIPresenter = mainPresenter;
    }

    @Override
    protected void initViewWithListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
