package com.code19.appmanager.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.code19.appmanager.ui.fragment.AppFragment;

/**
 * Created by gh0st on 2017/2/17.
 */

public class FragmentUtils {
    private static FragmentUtils mFactory = null;

    public static FragmentUtils getInstance() {
        if (mFactory == null) {
            mFactory = new FragmentUtils();
        }
        return mFactory;
    }

    public int getFragmentSize() {
        return 2;
    }

    public Fragment getFragment(int position) {
        SparseArray<Fragment> map = new SparseArray<Fragment>();
        Fragment fragment = null;
        if (map.get(position, fragment) != null) {
            return map.get(position);
        }
        switch (position) {
            case 0:
                fragment = new AppFragment();
                Bundle bundle0 = new Bundle();
                bundle0.putInt("position", 0);
                fragment.setArguments(bundle0);
                break;
            case 1:
                fragment = new AppFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("position", 1);
                fragment.setArguments(bundle1);
                break;
        }
        map.put(position, fragment);
        return fragment;
    }
}
