package com.robert.zhihu.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.robert.zhihu.R;
import com.robert.zhihu.adapter.PopularPageAdapter;
import com.robert.zhihu.base.BaseActivity;
import com.robert.zhihu.contract.MainContract;
import com.robert.zhihu.presenter.MainPresenter;
import com.robert.zhihu.ui.fragment.ItemFragment;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    private PopularPageAdapter popularPageAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_content;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initEventAndData() {
        mIPresenter.getTabs();
    }

    @Override
    public void addTabs(List<String> tabs) {
        popularPageAdapter = new PopularPageAdapter(getSupportFragmentManager());
        for (String tab : tabs) {
            ItemFragment itemFragment = ItemFragment.newInstance(tab);
            popularPageAdapter.addFragment(tab, itemFragment);
        }
        mViewPager.setAdapter(popularPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
