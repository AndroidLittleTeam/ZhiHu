package com.robert.zhihu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.robert.zhihu.entity.Popular;

/**
 * Created by robert on 2016/8/12.
 */

public class PopularRecycleAdapter extends BaseLoadMoreAdapter<Popular, PopularRecycleAdapter.PopularRecycleViewHolder> {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class PopularRecycleViewHolder extends RecyclerView.ViewHolder {

        public PopularRecycleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
