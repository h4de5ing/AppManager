package com.code19.appmanager.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.code19.appmanager.R;
import com.code19.appmanager.model.AppModel;
import com.code19.library.AppUtils;
import com.code19.library.DateUtils;
import com.code19.library.FileUtils;
import com.code19.library.SpannableStringUtils;

/**
 * Created by gh0st on 2017/2/17.
 */

public class ViewUtils {
    public static void appInfoDialog(Activity activity, AppModel appModel) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setCancelable(true);
        dialog.setIcon(appModel.getAppIcon());
        dialog.setTitle(appModel.getAppName());
        View view = View.inflate(activity, R.layout.dialog_app_info, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.tv_app_info);
        String packname = appModel.getAppPack();
        tvInfo.setText(SpannableStringUtils.getBuilder(activity, "包名:")
                .append(packname)
                .append("\nAPK位置：" + AppUtils.getAppApk(activity, packname))
                .append("\n数据目录：" + AppUtils.getAppApk(activity, packname))
                .append("\n版本名:" + AppUtils.getAppVersionName(activity, packname))
                .append("\n版本号:" + AppUtils.getAppVersionCode(activity, packname))
                .append("\n文件大小:" + FileUtils.formatFileSize(activity, AppUtils.getAppSize(activity, packname)))
                .append("\n安装时间:" + DateUtils.formatDataTime(AppUtils.getAppDate(activity, packname)))
                .append("\n更新时间:" + DateUtils.formatDataTime(AppUtils.getAppDate(activity, packname)))
                .append("\nTargetSDKVersion:" + DateUtils.formatDataTime(AppUtils.getAppDate(activity, packname)))
                .append("\nUID:" + DateUtils.formatDataTime(AppUtils.getAppDate(activity, packname)))
                .append("\n签名信息:" + AppUtils.getAppSign(activity, packname))
                .create());
        dialog.setView(view);
        if (!activity.isFinishing()) {
            dialog.show();
        }
    }

}
