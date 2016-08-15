package com.robert.zhihu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.robert.zhihu.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by robert on 2016/8/15.
 */

public class ProgressLayout extends RelativeLayout {

    private static final String TAG_LOADING_DATA = "loading_data";
    private static final String TAG_ERROR = "error";
    private static final Object TAG_NO_DATA = "no_data";

    private LayoutInflater inflater;
    List<View> contentViews = new ArrayList<>();
    private RelativeLayout viewLoading;
    private LayoutParams layoutParams;
    private RelativeLayout viewError;
    private RelativeLayout viewNoData;
    private Button btnTry;


    public ProgressLayout(Context context) {
        super(context);
        init();
    }

    public ProgressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(CENTER_IN_PARENT);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (child.getTag() == null || (
                !child.getTag().equals(TAG_LOADING_DATA)
                        && !child.getTag().equals(TAG_ERROR)
                        && !child.getTag().equals(TAG_NO_DATA))) {
            contentViews.add(child);
        }
    }

    public void showLoading(){

    }


    public void showLoadingView() {
        if (viewLoading == null) {
            viewLoading = (RelativeLayout) inflater.inflate(R.layout.layout_loading_view, null);
            viewLoading.setTag(TAG_LOADING_DATA);
            this.addView(viewLoading, layoutParams);
        } else {
            viewLoading.setVisibility(VISIBLE);
        }
    }

    public void showErrorView(String msg) {
        if (viewError == null) {
            viewError = (RelativeLayout) inflater.inflate(R.layout.layout_error_view, null);
            viewError.setTag(TAG_ERROR);
            btnTry = (Button) viewError.findViewById(R.id.btn_try);
            ((TextView) viewError.findViewById(R.id.tv_error)).setText(msg);
            this.addView(viewError, layoutParams);
        } else {
            viewError.setVisibility(VISIBLE);
        }
    }

    public void showNoDataView() {
        if (viewNoData == null) {
            viewNoData = (RelativeLayout) inflater.inflate(R.layout.layout_no_data_view, null);
            viewNoData.setTag(TAG_NO_DATA);
            btnTry = (Button) viewError.findViewById(R.id.btn_try);
            this.addView(viewNoData, layoutParams);
        } else {
            viewNoData.setVisibility(VISIBLE);
        }
    }

    public void hideLoadingView() {
        if (viewLoading != null && viewLoading.getVisibility() == VISIBLE) {
            viewLoading.setVisibility(GONE);
        }
    }

    public void hideErrorView() {
        if (viewError != null && viewError.getVisibility() == VISIBLE) {
            viewError.setVisibility(GONE);
        }
    }

    public void hideNoDataView() {
        if (viewNoData != null && viewNoData.getVisibility() == VISIBLE) {
            viewNoData.setVisibility(GONE);
        }
    }
}
