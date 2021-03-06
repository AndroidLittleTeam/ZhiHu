package com.robert.zhihu.base;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by robert on 2016/8/11.
 */

public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    public BasePresenter() {

    }

    @Override
    public void attachView(@NonNull T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        removeSubscribe();
    }

    public void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription != null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void removeSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

}
