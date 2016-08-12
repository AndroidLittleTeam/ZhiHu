package com.robert.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.robert.zhihu.ui.fragment.ItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 2016/8/12.
 */

public class PopularPageAdapter extends FragmentPagerAdapter {

    private List<String> mTabs;
    private List<ItemFragment> mItemFragments;

    public PopularPageAdapter(FragmentManager fm) {
        super(fm);
        mTabs = new ArrayList<>();
        mItemFragments = new ArrayList<>();
    }

    public void addFragment(String title, ItemFragment fragment) {
        this.mTabs.add(title);
        this.mItemFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mItemFragments.get(position);
    }

    @Override
    public int getCount() {
        return mItemFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position);
    }
}
