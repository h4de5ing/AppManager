package com.code19.appmanager;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.code19.appmanager.fragment.CollectionAppFragment;
import com.code19.appmanager.fragment.SystemAppFragment;
import com.code19.appmanager.fragment.UserAppFragment;

/**
 * Created by gh0st on 2017/2/17.
 */

public class FragmentUtils {
    private static FragmentUtils mFactory = null;

    public static FragmentUtils getInstatic() {
        if (mFactory == null) {
            mFactory = new FragmentUtils();
        }
        return mFactory;
    }

    public int getFragmentSize() {
        return 3;
    }

    public Fragment getFragment(int position) {
        SparseArray<Fragment> map = new SparseArray<Fragment>();
        Fragment fragment = null;
        if (map.get(position, fragment) != null) {
            return map.get(position);
        }
        switch (position) {
            case 0:
                fragment = new UserAppFragment();
                break;
            case 1:
                fragment = new SystemAppFragment();
                break;
            case 2:
                fragment = new CollectionAppFragment();
                break;
        }
        map.put(position, fragment);
        return fragment;
    }
}
