package com.robert.zhihu.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.robert.zhihu.Base.BasePresenter;
import com.robert.zhihu.data.api.HotApi;
import com.robert.zhihu.ui.MainView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by robert on 2016/8/8.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private static final String TAG = "MainPresenter";
    private HotApi mHotApi;
    private MainView mMainView;

    @Inject
    public MainPresenter(HotApi hotApi) {
        this.mHotApi = hotApi;
    }

    @Override
    public void attachView(@NonNull MainView view) {
        this.mMainView = view;
    }

    @Override
    public void detachView() {

    }

    public void getData() {
        mHotApi.getPopular(1, 10, "美女")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listApiResponse -> {
                    Log.d(TAG, listApiResponse.toString());
                }, throwable -> {
                    Log.d(TAG, throwable.toString());
                });
    }
}
