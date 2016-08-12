package com.robert.zhihu.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.robert.zhihu.R;
import com.robert.zhihu.base.BaseFragment;
import com.robert.zhihu.contract.ItemContract;
import com.robert.zhihu.presenter.ItemPresenter;

import butterknife.BindView;

/**
 * Created by robert on 2016/8/9.
 */

public class ItemFragment extends BaseFragment<ItemPresenter> implements ItemContract.View {

    public static final String TYPE = "type";

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initListener(View view) {

    }

    @Override
    public View inflateView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_item, null);
    }

    public static ItemFragment newInstance(String tab) {
        ItemFragment itemFragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, tab);
        itemFragment.setArguments(bundle);
        return itemFragment;
    }
}
