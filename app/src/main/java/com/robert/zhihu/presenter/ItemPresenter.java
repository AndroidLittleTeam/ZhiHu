package com.robert.zhihu.presenter;

import com.robert.zhihu.base.BasePresenter;
import com.robert.zhihu.contract.ItemContract;

import javax.inject.Inject;

/**
 * Created by robert on 2016/8/11.
 */

public class ItemPresenter extends BasePresenter<ItemContract.View> implements ItemContract.Presenter {

    @Inject
    public ItemPresenter() {
        super();
    }

}
