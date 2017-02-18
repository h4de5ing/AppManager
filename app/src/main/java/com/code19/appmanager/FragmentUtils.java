package com.code19.appmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

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
                Bundle bundle0 = new Bundle();
                bundle0.putInt("position", 0);
                fragment.setArguments(bundle0);
                break;
            case 1:
                fragment = new UserAppFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("position", 1);
                fragment.setArguments(bundle1);
                break;
            case 2:
                fragment = new UserAppFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("position", 2);
                fragment.setArguments(bundle2);
                break;
        }
        map.put(position, fragment);
        return fragment;
    }
}
