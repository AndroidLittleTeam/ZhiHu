package com.robert.zhihu.ui.impl.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.robert.zhihu.Base.BaseFragment;
import com.robert.zhihu.presenter.ItemPresenter;

import javax.inject.Inject;

/**
 * Created by robert on 2016/8/9.
 */

public class ItemFragment extends BaseFragment {


    @Inject
    ItemPresenter mItemPresenter;

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initListener(View view) {

    }

    @Override
    public View inflateView(LayoutInflater inflater) {
        return null;
    }
}
