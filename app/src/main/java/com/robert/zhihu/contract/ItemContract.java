package com.robert.zhihu.contract;

import com.robert.zhihu.base.IPresenter;
import com.robert.zhihu.base.IView;
import com.robert.zhihu.entity.Popular;

import java.util.List;

/**
 * Created by robert on 2016/8/12.
 */

public class ItemContract {
    public interface View extends IView {
        /**
         * 刷新数据
         *
         * @param populars
         */
        void refreshData(List<Popular> populars);

        /**
         * 加载更多
         *
         * @param populars
         */
        void loadMoreData(List<Popular> populars);

        void hideRefreshView();

        void showRefreshView();

    }

    public interface Presenter extends IPresenter<View> {

    }
}
