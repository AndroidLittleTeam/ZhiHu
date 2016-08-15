package com.robert.zhihu.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.robert.zhihu.R;
import com.robert.zhihu.adapter.PopularPageAdapter;
import com.robert.zhihu.base.BaseActivity;
import com.robert.zhihu.contract.MainContract;
import com.robert.zhihu.presenter.MainPresenter;
import com.robert.zhihu.ui.fragment.ItemFragment;
import com.wkw.common_lib.utils.ViewUtils;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.nav)
    NavigationView mNavigation;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private PopularPageAdapter popularPageAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initEventAndData() {
        mIPresenter.getTabs();
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigation.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        Snackbar.make(ViewUtils.getRootView(MainActivity.this), item.getTitle(), Snackbar.LENGTH_SHORT).show();
        return false;
    }
}
