package com.code19.appmanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.code19.library.AppUtils;
import com.code19.library.DensityUtil;
import com.code19.library.FileUtils;

/**
 * Created by gh0st on 2017/2/17.
 */

public class ViewUtils {
    public static void showAppPopup(final Context context, final AppModel bean, View anchor) {
        View contentView = View.inflate(context, R.layout.popwin_app_layout, null);
        int width = DensityUtil.dip2px(context, 100);
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow window = new PopupWindow(contentView, width, height);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        window.showAsDropDown(anchor, 60, -anchor.getHeight());
        contentView.findViewById(R.id.pop_tv_uninstall).setVisibility(bean.isSystem() ? View.GONE : View.VISIBLE);
        contentView.findViewById(R.id.pop_tv_collection).setVisibility(bean.isSystem() ? View.GONE : View.VISIBLE);
        contentView.findViewById(R.id.pop_tv_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.runApp(context, bean.getAppPack());
                window.dismiss();
            }
        });
        contentView.findViewById(R.id.pop_tv_uninstall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.uninstallApk(context, bean.getAppPack());
                window.dismiss();
            }
        });
        contentView.findViewById(R.id.pop_tv_collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileUtils.shareFile(context, "分享Apk", bean.getAppApk());
                window.dismiss();
            }
        });
        contentView.findViewById(R.id.pop_tv_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("package:" + bean.getAppPack()));
                context.startActivity(intent);
                window.dismiss();
            }
        });
    }
}
