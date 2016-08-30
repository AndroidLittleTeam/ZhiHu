package com.robert.zhihu.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.robert.zhihu.R;
import com.robert.zhihu.entity.Popular;
import com.robert.zhihu.utils.GlideManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robert on 2016/8/12.
 */

public class PopularRecycleAdapter extends BaseLoadMoreAdapter<Popular, PopularRecycleAdapter.PopularRecycleViewHolder> {


    private OnItemClickListener onItemClickListener;

    @Override
    protected PopularRecycleViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot, parent, false);
        return new PopularRecycleViewHolder(view);
    }

    @Override
    protected void onBindItemViewHolder(PopularRecycleViewHolder holder, Popular popular, int position) {
        GlideManager.loadImg(popular.getPicUrl(), holder.imgItem, holder.imgItem.getContext());
        holder.tvTitle.setText(popular.getTitle());
        holder.tvContent.setText(popular.getDescription());
        holder.cardView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(popular.getUrl(), popular.getTitle());
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    static class PopularRecycleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_item)
        ImageView imgItem;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.card_view)
        CardView cardView;

        public PopularRecycleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onClick(String url, String title);
    }
}
