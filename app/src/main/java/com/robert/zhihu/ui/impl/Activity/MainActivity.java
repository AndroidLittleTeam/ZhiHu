package com.robert.zhihu.ui.impl.Activity;

import android.view.View;

import com.robert.zhihu.Base.BaseActivity;
import com.robert.zhihu.R;
import com.robert.zhihu.presenter.MainPresenter;
import com.robert.zhihu.ui.MainView;

import javax.inject.Inject;

import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
        mainPresenter.attachView(this);
        mIPresenter = mainPresenter;
    }

    @Override
    protected void initViewWithListener() {

    }

    @OnClick(R.id.btn_get)
    public void get(View view) {
        mainPresenter.getData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
