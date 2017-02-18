package com.code19.appmanager.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by gh0st on 2017/2/18.
 */

public class AppInstallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_PACKAGE_ADDED)) {
            String pkgName = intent.getDataString().substring(8);
            Log.d("ghost", pkgName + " app installed ");
        } else if (action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
            String pkgName = intent.getDataString().substring(8);
            Log.d("ghost", pkgName + " app uninstalled");
        }
    }
}
