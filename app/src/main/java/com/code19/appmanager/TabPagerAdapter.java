package com.code19.appmanager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by gh0st on 2017/2/17.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentUtils.getInstatic().getFragment(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getStringArray(R.array.tab_nav)[position];
    }

    @Override
    public int getCount() {
        return FragmentUtils.getInstatic().getFragmentSize();
    }
}
