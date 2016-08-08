package com.robert.zhihu.Base;

import android.support.annotation.NonNull;

/**
 * Created by robert on 2016/8/8.
 */

public interface IPresenter<T extends IView> {
    /**
     * 关联View，使之可以和view关联
     * @param view
     */
    void attachView(@NonNull T view);

    /**
     * 释放view的相关资源
     */
    void detachView();
}
