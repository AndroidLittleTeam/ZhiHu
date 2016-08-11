package com.robert.zhihu.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.robert.zhihu.App;
import com.robert.zhihu.injector.component.DaggerFragmentComponent;
import com.robert.zhihu.injector.component.FragmentComponent;
import com.robert.zhihu.injector.module.FragmentModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by robert on 2016/8/8.
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected FragmentComponent mFragmentComponent;
    private Unbinder unbinder;
    protected IPresenter mIPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflateView(inflater);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();

    }

    /**
     * 初始化注入
     */
    public abstract void initInjector();

    /**
     * 初始化监听器
     */
    public abstract void initListener(View view);

    /**
     * 初始化UI
     *
     * @param inflater 布局渲染器
     * @return View
     */
    public abstract View inflateView(LayoutInflater inflater);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mIPresenter != null) {
            mIPresenter.detachView();
        } else {
            Logger.wtf(this.getClass().getSimpleName(), "this fragment " + this.getClass().getSimpleName() + "  没有设置 IPresenter!!!");
        }
    }
}
