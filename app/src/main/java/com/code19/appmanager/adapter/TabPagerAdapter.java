package com.code19.appmanager.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.code19.appmanager.R;
import com.code19.appmanager.utils.FragmentUtils;

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
        return FragmentUtils.getInstance().getFragment(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getStringArray(R.array.tab_nav)[position];
    }

    @Override
    public int getCount() {
        return FragmentUtils.getInstance().getFragmentSize();
    }
}
