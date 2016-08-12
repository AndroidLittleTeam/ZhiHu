package com.robert.zhihu.contract;

import com.robert.zhihu.base.IPresenter;
import com.robert.zhihu.base.IView;

import java.util.List;

/**
 * Created by robert on 2016/8/12.
 */

public class MainContract {

    public interface View extends IView {
        void addTabs(List<String> tabs);
    }

    public interface Presenter extends IPresenter<View> {
        void getTabs();
    }
}
