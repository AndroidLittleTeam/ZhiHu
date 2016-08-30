package com.robert.zhihu.presenter;

import com.robert.zhihu.base.BasePresenter;
import com.robert.zhihu.contract.ItemContract;
import com.robert.zhihu.data.DataManager;
import com.robert.zhihu.entity.Popular;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by robert on 2016/8/11.
 */

public class ItemPresenter extends BasePresenter<ItemContract.View> implements ItemContract.Presenter {
    public int pageNumber;
    private DataManager mDataManager;

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Inject
    public ItemPresenter(DataManager dataManager) {
        super();
        this.mDataManager = dataManager;
        pageNumber = 1;
    }

    public void getItems(String type) {
        mDataManager.getPopular(pageNumber, type).subscribe(new Subscriber<List<Popular>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.hideRefreshView();
            }

            @Override
            public void onNext(List<Popular> populars) {
                if (pageNumber == 1) {
                    mView.refreshData(populars);
                } else {
                    mView.loadMoreData(populars);
                }
                mView.hideRefreshView();
            }
        });
    }
}
