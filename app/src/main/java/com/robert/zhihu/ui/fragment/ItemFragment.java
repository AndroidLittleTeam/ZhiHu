package com.robert.zhihu.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.robert.zhihu.R;
import com.robert.zhihu.adapter.PopularRecycleAdapter;
import com.robert.zhihu.base.BaseFragment;
import com.robert.zhihu.contract.ItemContract;
import com.robert.zhihu.entity.Popular;
import com.robert.zhihu.presenter.ItemPresenter;
import com.robert.zhihu.ui.activity.WebActivity;

import java.util.List;

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

    PopularRecycleAdapter mPopularAdapter;
    private String type;

    public static ItemFragment newInstance(String tab) {
        ItemFragment itemFragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, tab);
        itemFragment.setArguments(bundle);
        return itemFragment;
    }

    @Override
    protected void initEventAndData() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.BLACK);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mIPresenter.setPageNumber(1);
            mIPresenter.getItems(type);
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int lastVisibleItemPosition;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == recyclerView.getAdapter().getItemCount()) {
                    if (mPopularAdapter.canLoadMore()) {
                        mPopularAdapter.setLoading(true);
                        mIPresenter.pageNumber++;
                        mIPresenter.getItems(type);
                    }
                }
            }
        });
        mPopularAdapter = new PopularRecycleAdapter();
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mPopularAdapter);

        mPopularAdapter.setOnItemClickListener(new PopularRecycleAdapter.OnItemClickListener() {
            @Override
            public void onClick(String url, String title) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        mSwipeRefreshLayout.post(this::showRefreshView);
        type = getArguments().getString(TYPE);
        mIPresenter.getItems(type);
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public View inflateView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_item, null);
    }

    @Override
    public void refreshData(List<Popular> populars) {
        mPopularAdapter.refreshData(populars);
    }

    @Override
    public void loadMoreData(List<Popular> populars) {
        mPopularAdapter.loadMoreData(populars);
    }

    @Override
    public void hideRefreshView() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRefreshView() {
        mSwipeRefreshLayout.setRefreshing(true);
    }


}
