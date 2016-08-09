package com.robert.zhihu.presenter;

import android.support.annotation.NonNull;

import com.robert.zhihu.Base.IPresenter;
import com.robert.zhihu.ui.MainView;

import javax.inject.Inject;

/**
 * Created by robert on 2016/8/8.
 */

public class MainPresenter implements IPresenter<MainView> {

    @Inject
    public MainPresenter() {

    }

    @Override
    public void attachView(@NonNull MainView view) {

    }

    @Override
    public void detachView() {

    }
}
