package com.robert.zhihu.presenter;

import com.robert.zhihu.base.BasePresenter;
import com.robert.zhihu.contract.MainContract;
import com.robert.zhihu.data.DataManager;

import javax.inject.Inject;

/**
 * Created by robert on 2016/8/8.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public MainPresenter() {
        super();
//        this.mDataManager = dataManager;
    }

    @Override
    public void getTabs() {
        mView.addTabs(mDataManager.getTabs());
    }
}
