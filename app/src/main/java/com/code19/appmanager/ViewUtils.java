package com.code19.appmanager;

import android.content.Context;

/**
 * Created by gh0st on 2017/2/17.
 */

public class ViewUtils {
    public static void showAppPopup(Context context, AppModel appModel) {
        AppPopupWindow appPopupWindow = new AppPopupWindow(context, appModel);
        appPopupWindow.setCancelable(true);
        appPopupWindow.setCanceledOnTouchOutside(true);
        appPopupWindow.show();
    }
}
