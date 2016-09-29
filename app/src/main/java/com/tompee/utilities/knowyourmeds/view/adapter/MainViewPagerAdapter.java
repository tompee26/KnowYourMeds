package com.tompee.utilities.knowyourmeds.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tompee.utilities.knowyourmeds.view.fragment.RecentFavoriteFragment;
import com.tompee.utilities.knowyourmeds.view.fragment.SearchFragment;
import com.tompee.utilities.knowyourmeds.view.fragment.SettingsFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 3;
    private final SearchFragment mSearchFragment;
    private final RecentFavoriteFragment mRecentFavoriteFragment;
    private final SettingsFragment mSettingsFragment;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mSearchFragment = SearchFragment.newInstance();
        mRecentFavoriteFragment = RecentFavoriteFragment.newInstance();
        mSettingsFragment = SettingsFragment.newInstance();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mRecentFavoriteFragment;
            case 1:
                return mSearchFragment;
            case 2:
                return mSettingsFragment;
        }
        return mSearchFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
