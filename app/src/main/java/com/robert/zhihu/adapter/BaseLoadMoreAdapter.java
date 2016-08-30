package com.robert.zhihu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.robert.zhihu.R;
import com.robert.zhihu.common.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 2016/8/15.
 */

public abstract class BaseLoadMoreAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    private static final int TYPE_FOOTER = Integer.MAX_VALUE;
    private static final int ITEM_TYPE = 0;
    private final List<T> mData = new ArrayList<>();

    private boolean hasMore = true, isLoading = true;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            if (mData.size() == 0) {
                ((FooterViewHolder) holder).itemView.setVisibility(View.GONE);
            } else {
                ((FooterViewHolder) holder).itemView.setVisibility(View.VISIBLE);
                if (hasMore) {
                    ((FooterViewHolder) holder).progressBar.setVisibility(View.VISIBLE);
                    ((FooterViewHolder) holder).tvContent.setText("正在加载");
                } else {
                    ((FooterViewHolder) holder).progressBar.setVisibility(View.GONE);
                    ((FooterViewHolder) holder).tvContent.setText("已全部加载");
                }
            }
        } else {
            onBindItemViewHolder((VH) holder, mData.get(position), position);
        }
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    protected abstract void onBindItemViewHolder(VH holder, T t, int position);


    public boolean canLoadMore() {
        return hasMore && !isLoading;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_load_more, parent, false);
            return new FooterViewHolder(view);
        } else {
            return onCreateItemViewHolder(parent, viewType);
        }
    }

    protected abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    public void refreshData(List<T> data) {
        if (data == null) return;
        this.mData.clear();
        this.mData.addAll(data);
        this.hasMore = data.size() == Constants.PAGE_SIZE;
        this.isLoading = false;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mData.size()) {
            return TYPE_FOOTER;
        } else {
            return ITEM_TYPE;
        }
    }

    public void loadMoreData(List<T> data) {
        if (data == null) return;
        this.mData.addAll(data);
        this.hasMore = data.size() == Constants.PAGE_SIZE;
        this.isLoading = false;
        notifyDataSetChanged();
    }

    private static class FooterViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;
        ProgressBar progressBar;

        FooterViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_view);
        }
    }
}
