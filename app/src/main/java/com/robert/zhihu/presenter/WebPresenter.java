package com.robert.zhihu.presenter;

import com.robert.zhihu.base.BasePresenter;
import com.robert.zhihu.contract.WebContract;
import com.robert.zhihu.data.DataManager;

import javax.inject.Inject;

/**
 * Created by robert on 2016/8/30.
 */

public class WebPresenter extends BasePresenter<WebContract.View> implements WebContract.Presenter {

    @Inject
    public WebPresenter(DataManager dataManager){
        super();
    }


}
